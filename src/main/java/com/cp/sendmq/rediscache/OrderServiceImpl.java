package com.cp.sendmq.rediscache;

import com.cp.sendmq.entity.Order;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @Cacheable(cacheNames = "catalog_test_id", key = "#id")
    public Order selectOrderById(String id) {
        Order order = new Order();
        order.setId("99");
        order.setContent("ppp");
        order.setName("第一");
        System.out.println("db查询来了==============");
        return order;
    }

    @Override
    @CachePut(key = "#id")
    public Order updateOrderById(String id) {

        Order order = new Order();
        order.setId("99");
        order.setContent("ppp");
        order.setName("第二");
        System.out.println("db查询来了==============");
        return order;
    }

    @Override
    @CacheEvict(key = "#id", beforeInvocation = true)
    public void deleteOrderById(String id) {
        System.out.println("delete");
    }


    @Override
    @Cacheable(cacheNames = "catalog_test_id", key = "#id", unless = "#result == null")
    public String selectStringById(String id) {
        System.out.println("db222查询来了==============");
        return null;
    }


    @Override
    @Cacheable(key = "#order.name", condition = "#order.id eq '99'")
    public Order selectOrderByparam(Order order) {
        System.out.println("db333查询来了==============");
        Order order1 = new Order();
        order1.setId("99");
        order1.setContent("ppp");
        order1.setName("第一");
        return order1;
    }


}