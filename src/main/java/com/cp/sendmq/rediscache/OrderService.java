package com.cp.sendmq.rediscache;

import com.cp.sendmq.entity.Order;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */
public interface OrderService {
    Order selectOrderById(String id);

    String selectStringById(String id);

    Order updateOrderById(String id);

    Order selectOrderByparam(Order order);

    void deleteOrderById(String id);
}
