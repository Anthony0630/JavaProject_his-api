package org.example.his.api.front.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.map.MapUtil;
import org.example.his.api.common.R;
import org.example.his.api.config.sa_token.StpCustomerUtil;
import org.example.his.api.front.controller.form.LoginForm;
import org.example.his.api.front.controller.form.SendSmsCodeForm;
import org.example.his.api.front.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController("FrontCustomerController")
@RequestMapping("/front/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @PostMapping("/sendSmsCode")
    public R sendSmsCode(@RequestBody @Valid SendSmsCodeForm form) {
        boolean bool = customerService.sendSmsCode(form.getTel());
        String msg = bool ? "短信验证码已发送" : "无法发送短信验证码";
        return R.ok(msg).put("result", bool);
    }

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginForm form) {
        HashMap map = customerService.login(form.getTel(), form.getCode());
        boolean result = MapUtil.getBool(map, "result");
        String msg = MapUtil.getStr(map, "msg");
        R r = R.ok(msg).put("result", result);
        if (result) {
            //生成令牌
            int id = MapUtil.getInt(map, "id");
            StpCustomerUtil.login(id, "PC");
            String token = StpCustomerUtil.getTokenValue();
            r.put("token", token);
        }
        return r;
    }

    @GetMapping("/logout")
    @SaCheckLogin(type = StpCustomerUtil.TYPE)
    public R logout() {
        int id = StpCustomerUtil.getLoginIdAsInt();
        StpCustomerUtil.logout(id, "PC");
        return R.ok();
    }

    @GetMapping("/checkLogin")
    public R checkLogin() {
        boolean bool = StpCustomerUtil.isLogin();
        return R.ok().put("result", bool);
    }

    @GetMapping("/searchSummary")
    @SaCheckLogin(type = StpCustomerUtil.TYPE)
    public R searchSummary() {
        int id = StpCustomerUtil.getLoginIdAsInt();
        HashMap map = customerService.searchSummary(id);
        return R.ok().put("result", map);
    }
}
