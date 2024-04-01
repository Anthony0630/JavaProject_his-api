package org.example.his.api.mis.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.example.his.api.common.PageUtils;
import org.example.his.api.common.R;
import org.example.his.api.exception.HisException;
import org.example.his.api.mis.controller.form.CheckPaymentResultForm;
import org.example.his.api.mis.controller.form.DeleteOrderByIdForm;
import org.example.his.api.mis.controller.form.SearchOrderByPageForm;
import org.example.his.api.mis.controller.form.UpdateRefundStatusByIdForm;
import org.example.his.api.mis.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController("MisOrderController")
@RequestMapping("/mis/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/searchByPage")
    @SaCheckPermission(value = {"ROOT", "ORDER:SELECT"}, mode = SaMode.OR)
    public R searchByPage(@RequestBody @Valid SearchOrderByPageForm form) {
        if ((form.getStartDate() != null && form.getEndDate() == null) || (form.getStartDate() == null && form.getEndDate() != null)) {
            throw new HisException("startDate和endDate不允许一个为空，另一个不为空");
        }
        //验证日期先后逻辑
        else if (form.getStartDate() != null && form.getEndDate() != null) {
            DateTime startDate = DateUtil.parse(form.getStartDate());
            DateTime endDate = DateUtil.parse(form.getEndDate());
            if (endDate.isBefore(startDate)) {
                throw new HisException("endDate不能早于startDate");
            }
        }
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        Map param = BeanUtil.beanToMap(form);
        param.put("start", start);
        PageUtils pageUtils = orderService.searchByPage(param);
        return R.ok().put("page", pageUtils);
    }

    @PostMapping("/checkPaymentResult")
    @SaCheckPermission(value = {"ROOT", "ORDER:UPDATE"}, mode = SaMode.OR)
    public R checkPaymentResult(@RequestBody @Valid CheckPaymentResultForm form) {
        int rows = orderService.checkPaymentResult(form.getOutTradeNoArray());
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteById")
    @SaCheckPermission(value = {"ROOT", "ORDER:DELETE"}, mode = SaMode.OR)
    public R deleteById(@RequestBody @Valid DeleteOrderByIdForm form) {
        int rows = orderService.deleteById(form.getId());
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateRefundStatusById")
    @SaCheckPermission(value = {"ROOT", "ORDER:UPDATE"}, mode = SaMode.OR)
    public R updateRefundStatusById(@RequestBody @Valid UpdateRefundStatusByIdForm form) {
        int rows = orderService.updateRefundStatusById(form.getId());
        return R.ok().put("rows", rows);
    }
}

