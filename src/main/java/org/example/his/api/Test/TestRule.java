package org.example.his.api.Test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class TestRule {
    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        String rule= """
                import java.math.BigDecimal;
                                
                p = new BigDecimal(price);
                n = new BigDecimal(number);
                                
                result = n.multiply(p).subtract(new BigDecimal(number / 2).multiply(new BigDecimal("0.5")).multiply(p)).toString();
        """;
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("price", 1000);
        context.put("number", 3);
        Object r = runner.execute(rule, context, null, true, false);
        System.out.println(r.toString());
    }
}


