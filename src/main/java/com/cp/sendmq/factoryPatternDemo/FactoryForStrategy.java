package com.cp.sendmq.factoryPatternDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    Map<String, ReceiveHandle> strategys = new ConcurrentHashMap<>(3);

    public ReceiveHandle getStrategy(String component) throws Exception {
        ReceiveHandle strategy = strategys.get(component);
        if (strategy == null) {
            throw new RuntimeException("no strategy defined");
        }
        return strategy;
    }
}
