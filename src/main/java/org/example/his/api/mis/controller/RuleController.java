package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.example.his.api.common.R;
import org.example.his.api.mis.service.RuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/mis/rule")
public class RuleController {
    @Resource
    private RuleService ruleService;

    @GetMapping("/searchAllRule")
    @SaCheckLogin
    public R searchAllRule() {
        ArrayList<HashMap> list = ruleService.searchAllRule();
        return R.ok().put("result", list);
    }
}

