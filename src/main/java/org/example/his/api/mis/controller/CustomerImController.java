package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.example.his.api.common.R;
import org.example.his.api.mis.service.impl.CustomerImServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController("MisCustomerImController")
@RequestMapping("/mis/customer/im")
public class CustomerImController {
    @Resource
    private CustomerImServiceImpl customerImService;

    @GetMapping("/searchServiceAccount")
    @SaCheckPermission(value = {"ROOT", "CUSTOMER_IM:SELECT"}, mode = SaMode.OR)
    public R searchServiceAccount() {
        HashMap result = customerImService.searchServiceAccount();
        return R.ok().put("result", result);
    }
}

