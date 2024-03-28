package org.example.his.api.front.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.example.his.api.db.dao.GoodsDao;
import org.example.his.api.db.dao.OrderDao;
import org.example.his.api.exception.HisException;
import org.example.his.api.front.service.OrderService;
import org.example.his.api.front.service.PaymentService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("FrontOrderServiceImpl")
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private PaymentService paymentService;

    @Resource
    private RedisTemplate redisTemplate;

    private String paymentNotifyUrl = "/front/order/paymentCallback";

    @Override
    @Transactional
    public HashMap createPayment(Map param) {
        int goodsId = MapUtil.getInt(param, "goodsId");
        Integer number = MapUtil.getInt(param, "number");
        int customerId = MapUtil.getInt(param, "customerId");

        //如果当天该客户有10个以上未付款订单或者5个以上退款订单，当天就无法下单
        boolean illegal = orderDao.searchIllegalCountInDay(customerId);
        if (illegal) {
            return null;
        }
        // 查找商品详情信息
        HashMap map = goodsDao.searchSnapshotNeededById(goodsId);
        String goodsCode = MapUtil.getStr(map, "code");
        String goodsTitle = MapUtil.getStr(map, "title");
        String goodsDescription = MapUtil.getStr(map, "description");
        String goodsImage = MapUtil.getStr(map, "image");
        BigDecimal goodsInitialPrice = new BigDecimal(MapUtil.getStr(map, "initialPrice"));
        BigDecimal goodsCurrentPrice = new BigDecimal(MapUtil.getStr(map, "currentPrice"));
        String goodsRuleName = MapUtil.getStr(map, "ruleName");
        String goodsRule = MapUtil.getStr(map, "rule");
        String goodsType = MapUtil.getStr(map, "type");
        String goodsMd5 = MapUtil.getStr(map, "md5");

        String temp = MapUtil.getStr(map, "checkup_1");
        List<Map> goodsCheckup_1 = temp != null ? JSONUtil.parseArray(temp).toList(Map.class) : null;

        temp = MapUtil.getStr(map, "checkup_2");
        List<Map> goodsCheckup_2 = temp != null ? JSONUtil.parseArray(temp).toList(Map.class) : null;

        temp = MapUtil.getStr(map, "checkup_3");
        List<Map> goodsCheckup_3 = temp != null ? JSONUtil.parseArray(temp).toList(Map.class) : null;

        temp = MapUtil.getStr(map, "checkup_4");
        List<Map> goodsCheckup_4 = temp != null ? JSONUtil.parseArray(temp).toList(Map.class) : null;

        temp = MapUtil.getStr(map, "checkup");
        List<Map> goodsCheckup = temp != null ? JSONUtil.parseArray(temp).toList(Map.class) : null;

        temp = MapUtil.getStr(map, "tag");
        List<String> goodsTag = temp != null ? JSONUtil.parseArray(temp).toList(String.class) : null;

        // 计算订单金额
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("number", number.intValue());
        context.put("price", goodsCurrentPrice.toString());

        String amount = null;
        if (goodsRule != null) {
            try {
                //执行规则引擎计算支付结果
                amount = runner.execute(goodsRule, context, null, true, false).toString();
            } catch (Exception e) {
                throw new HisException("规则引擎计算价格失败", e);
            }
        } else {
            amount = goodsCurrentPrice.multiply(new BigDecimal(number)).toString();
        }

        // 创建微信支付单
        //把付款金额从元转换成分
        int total = NumberUtil.mul(amount, "100").intValue();
        //生成商品订单流水号
        String outTradeNo = IdUtil.simpleUUID().toUpperCase();

        //付款过期时间为20分钟
        DateTime dateTime = new DateTime();
        dateTime.offset(DateField.MINUTE, 20);
        String timeExpire = dateTime.toString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        //创建支付单
        ObjectNode objectNode = paymentService.unifiedOrder(outTradeNo, total, "购买体检套餐", paymentNotifyUrl, timeExpire);

        String codeUrl = objectNode.get("code_url").textValue();

        // 创建支付单缓存，设置缓存过期时间
        /*
         * 把支付单的codeUrl缓存到Redis中，用于将来检测未付款订单是否可以付款
         * 比如客户进入订单列表页面，想要对未付款的订单付款，我们可以根据是否存
         * 在缓存，判定用户能否付款。这样可以省去调用微信支付接口查询付款单状态。
         */
        String key = "codeUrl_" + customerId + "_" + outTradeNo;
        redisTemplate.opsForValue().set(key, codeUrl);
        redisTemplate.expireAt(key, dateTime); //设置缓存过期时间

        //TODO 如果不存在该商品快照，就创建快照记录
        //TODO 保存订单记录
        //TODO 更新商品销量
        //TODO 付款二维码图片转换成base64字符串返回给前端
        return null;
    }
}

