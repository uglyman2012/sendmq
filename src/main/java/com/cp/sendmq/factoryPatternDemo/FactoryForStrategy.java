package com.cp.sendmq.factoryPatternDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/26
 */
@Service
public class FactoryForStrategy {
    //接口和抽象都可用
    @Autowired
    Map<String, ReceiveHandle2> strategys = new ConcurrentHashMap<>(3);
    @Autowired
    Map<String, ReceiveHandle2> strategys2 = new HashMap<>(3);
    public ReceiveHandle2 getStrategy(String component) throws Exception {
        ReceiveHandle2 receiveHandle2 = strategys2.get(component);
        ReceiveHandle2 strategy = strategys.get(component);
        if (strategy == null) {
            throw new RuntimeException("no strategy defined");
        }
        return strategy;
    }
}
