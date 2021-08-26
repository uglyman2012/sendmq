package com.cp.sendmq.factoryPatternDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/26
 */
@Component
public class Receive1 implements ReceiveHandle {
    private final Logger log = LoggerFactory.getLogger(ReceiveHandle.class);

    @Override
    public String getName() {
        return "order.pay.result";
    }

    @Override
    public void execute(String data) {
        log.info("驱动业务实现{},接收参数{}", getName(), data);
        log.info("订单支付成功");
    }

}
