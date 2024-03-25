package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.json.JSONUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.common.R;
import org.example.his.api.db.pojo.DeptEntity;
import org.example.his.api.mis.controller.form.*;
import org.example.his.api.mis.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mis/dept")
public class DeptController {
    @Resource
    private DeptService deptService;

    @GetMapping("/searchAllDept")
    public R searchAllDept() {
        ArrayList<HashMap> list = deptService.searchAllDept();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public R searchByPage(@Valid @RequestBody SearchDeptByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start", start);
        PageUtils pageUtils = deptService.searchByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "DEPT:INSERT"}, mode = SaMode.OR)
    public R insert(@Valid @RequestBody InsertDeptForm form) {
        DeptEntity dept = JSONUtil.parse(form).toBean(DeptEntity.class);
        int rows = deptService.insert(dept);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/searchById")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchDeptByIdForm form) {
        HashMap map = deptService.searchById(form.getId());
        return R.ok().put("result", map);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "DEPT:UPDATE"}, mode = SaMode.OR)
    public R update(@Valid @RequestBody UpdateDeptForm form) {
        DeptEntity dept = new DeptEntity();
        dept.setId(form.getId());
        dept.setDeptName(form.getDeptName());
        dept.setTel(form.getTel());
        dept.setEmail(form.getEmail());
        dept.setDesc(form.getDesc());
        int rows = deptService.update(dept);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteByIds")
    @SaCheckPermission(value = {"ROOT", "DEPT:DELETE"}, mode = SaMode.OR)
    public R deleteByIds(@Valid @RequestBody DeleteDeptByIdsForm form) {
        int rows = deptService.deleteByIds(form.getIds());
        return R.ok().put("rows", rows);
    }

}

