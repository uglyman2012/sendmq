package com.cp.sendmq.rediscache;

import com.cp.sendmq.bean.Order;

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
}
