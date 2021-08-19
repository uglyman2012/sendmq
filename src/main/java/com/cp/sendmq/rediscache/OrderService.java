package com.cp.sendmq.rediscache;

import com.cp.sendmq.entity.Order;
import com.cp.sendmq.entity.Student;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */
public interface OrderService {
    Student selectOrderById(String id);

    String selectStringById(String id);

    Student updateOrderById(Student student);

    Order selectOrderByparam(Order order);

    void deleteOrderById(String id);
}
