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
@Component("two")
public class Receive2 implements ReceiveHandle {
    private final Logger log = LoggerFactory.getLogger(ReceiveHandle.class);

    @Override
    public String getName() {
        return "order.create.result";
    }

    @Override
    public void execute(String data) {
        log.info("驱动业务实现{},接收参数{}", getName(), data);
        log.info("订单下单成");
    }
}
