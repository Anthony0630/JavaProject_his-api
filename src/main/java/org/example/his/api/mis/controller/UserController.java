package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.common.R;
import org.example.his.api.db.pojo.UserEntity;
import org.example.his.api.mis.controller.form.*;
import org.example.his.api.mis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mis/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginForm form) {
        //把Form对象转换成Map对象。因为Form对象中含有后端验证表达式，该对象仅用于Web层，不适合传给业务层
        Map param = BeanUtil.beanToMap(form);

        //获取登陆用户的主键值
        Integer userId = userService.login(param);
        if (userId != null) {
            /*
             * 实现同端互斥效果，把该用户Web端的令牌销毁。
             * 在其他浏览器上已经登陆的该账户，令牌就失效了，实现同端互斥。
             */
            StpUtil.logout(userId,"Web");

            //通过会话对象，向SaToken传递userId
            StpUtil.login(userId,"Web");
            //生成新的令牌字符串，标记该令牌是给Web端用户使用的
            String token = StpUtil.getTokenValueByLoginId(userId, "Web");
            //获取用户的权限列表
            List<String> permissions = StpUtil.getPermissionList();
            //向前端返回数据
            return R.ok().put("result", true).put("token", token).put("permissions", permissions);
        }
        //如果登陆失败，返回给前端的result是false
        return R.ok().put("result", false);
    }

    @GetMapping("/logout")
    @SaCheckLogin
    public R logout() {
        //从令牌中解密出来userId
        int userId = StpUtil.getLoginIdAsInt();
        //销毁令牌
        StpUtil.logout(userId, "Web");
        return R.ok();
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin
    public R updatePassword(@Valid @RequestBody UpdatePasswordForm form) {
        int userId = StpUtil.getLoginIdAsInt();
        HashMap param = new HashMap() {{
            put("userId", userId);
            put("password", form.getPassword());
            put("newPassword", form.getNewPassword());
        }};
        int rows = userService.updatePassword(param);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public R searchByPage(@Valid @RequestBody SearchUserByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        PageUtils pageUtils = userService.searchByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    public R insert(@Valid @RequestBody InsertUserForm form) {
        UserEntity user = BeanUtil.toBean(form, UserEntity.class);
        user.setStatus(1);
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        int rows = userService.insert(user);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchById")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchUserByIdForm form) {
        HashMap map = userService.searchById(form.getUserId());
        return R.ok().put("result", map);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    public R update(@Valid @RequestBody UpdateUserForm form) {
        Map param = BeanUtil.beanToMap(form);
        param.replace("role", JSONUtil.parseArray(form.getRole()).toString());
        int rows = userService.update(param);
        if (rows == 1) {
            //该用户的Web端、APP端、小程序端等，全部退出登陆
            StpUtil.logout(form.getUserId());
        }
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteByIds")
    @SaCheckPermission(value = {"ROOT", "USER:DELETE"}, mode = SaMode.OR)
    public R deleteByIds(@Valid @RequestBody DeleteUserByIdsForm form) {
        Integer userId = StpUtil.getLoginIdAsInt();
        if (ArrayUtil.contains(form.getIds(), userId)) {
            return R.error("您不能删除自己的帐户");
        }
        int rows = userService.deleteByIds(form.getIds());
        if (rows > 0) {
            for (Integer id : form.getIds()) {
                StpUtil.logout(id);
            }
        }
        return R.ok().put("rows", rows);
    }

    @PostMapping("/dismiss")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    public R dismiss(@RequestBody @Valid DismissForm form) {
        int rows = userService.dismiss(form.getUserId());
        if (rows > 0) {
            StpUtil.logout(form.getUserId().intValue());
        }
        return R.ok().put("rows", rows);
    }
}
