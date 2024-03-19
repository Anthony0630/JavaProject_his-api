package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.example.his.api.common.R;
import org.example.his.api.mis.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mis/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/searchAllPermission")
    @SaCheckPermission(value = {"ROOT", "ROLE:INSERT", "ROLE:UPDATE"}, mode = SaMode.OR)
    public R searchAllPermission() {
        ArrayList<HashMap> list = permissionService.searchAllPermission();
        return R.ok().put("list", list);
    }
}

