package com.cp.sendmq.rediscache;

import com.cp.sendmq.bean.Order;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */
@CacheConfig(cacheNames = "catalog_test_id")
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    @Cacheable(value = "ksk#30", key = "#id")
    public Order selectOrderById(String id) {
        Order order = new Order();
        order.setId("99");
        order.setContent("ppp");
        order.setName("第一");
        System.out.println("db查询来了==============");
        return order;
    }

    @Override
    @Cacheable(cacheNames = "catalog_test_id", key = "#id")
    public String selectStringById(String id) {
        System.out.println("db222查询来了==============");
        return null;
    }
}
