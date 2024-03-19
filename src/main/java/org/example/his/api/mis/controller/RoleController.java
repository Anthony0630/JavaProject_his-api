package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.common.R;
import org.example.his.api.db.pojo.RoleEntity;
import org.example.his.api.mis.controller.form.*;
import org.example.his.api.mis.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mis/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/searchAllRole")
    public R searchAllRole() {
        ArrayList<HashMap> list = roleService.searchAllRole();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public R searchByPage(@Valid @RequestBody SearchRoleByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        PageUtils pageUtils = roleService.searchByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "ROLE:INSERT"}, mode = SaMode.OR)
    public R insert(@Valid @RequestBody InsertRoleForm form) {
        RoleEntity role = new RoleEntity();
        role.setRoleName(form.getRoleName());
        role.setPermissions(JSONUtil.parseArray(form.getPermissions()).toString());
        role.setDesc(form.getDesc());
        int rows = roleService.insert(role);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchById")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchRoleByIdForm form) {
        HashMap map = roleService.searchById(form.getId());
        return R.ok().put("result",map);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "ROLE:UPDATE"}, mode = SaMode.OR)
    public R update(@Valid @RequestBody UpdateRoleForm form) {
        RoleEntity role = new RoleEntity();
        role.setId(form.getId());
        role.setRoleName(form.getRoleName());
        role.setPermissions(JSONUtil.parseArray(form.getPermissions()).toString());
        role.setDesc(form.getDesc());
        int rows = roleService.update(role);
        //如果用户修改成功，并且用户修改了该角色的关联权限
        if (rows == 1 && form.getChanged()) {
            //把该角色关联的用户踢下线
            ArrayList<Integer> list = roleService.searchUserIdByRoleId(form.getId());
            list.forEach(userId -> {
                StpUtil.logout(userId);
            });
        }
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteByIds")
    @SaCheckPermission(value = {"ROOT", "ROLE:DELETE"}, mode = SaMode.OR)
    public R deleteByIds(@Valid @RequestBody DeleteRoleByIdsForm form) {
        int rows = roleService.deleteByIds(form.getIds());
        return R.ok().put("rows", rows);
    }
}
