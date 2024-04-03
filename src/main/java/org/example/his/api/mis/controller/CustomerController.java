package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.example.his.api.common.R;
import org.example.his.api.mis.controller.form.SearchCustomerSummaryForm;
import org.example.his.api.mis.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController("MisCustomerController")
@RequestMapping("/mis/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/searchSummary")
    @SaCheckLogin
    public R searchSummary(@RequestBody @Valid SearchCustomerSummaryForm form) {
        HashMap map = customerService.searchSummary(form.getCustomerId());
        return R.ok().put("result", map);
    }
}


