package org.example.his.api.front.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.example.his.api.common.R;
import org.example.his.api.config.sa_token.StpCustomerUtil;
import org.example.his.api.front.service.CustomerImService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController("FrontCustomerImController")
@RequestMapping("/front/customer/im")
public class CustomerImController {
    @Resource
    private CustomerImService customerImService;

    @GetMapping("/createAccount")
    @SaCheckLogin(type = StpCustomerUtil.TYPE)
    public R createAccount() {
        int customerId = StpCustomerUtil.getLoginIdAsInt();
        HashMap result = customerImService.createAccount(customerId);
        return R.ok().put("result", result);
    }
}

