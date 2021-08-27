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
public class Receive3 extends ReceiveHandle2 {
    private final Logger log = LoggerFactory.getLogger(ReceiveHandle.class);

    @Override
    public String getName() {
        return "order.create.result3";
    }
}
