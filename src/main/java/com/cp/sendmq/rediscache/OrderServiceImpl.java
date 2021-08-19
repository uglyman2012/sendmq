package com.cp.sendmq.rediscache;

import com.cp.sendmq.dao.StudentDao;
import com.cp.sendmq.entity.Order;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

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
    private final AtomicLong puts = new AtomicLong();
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;
    private int a = 0;
    @Override
    @Cacheable(cacheNames = "catalog_test_id", key = "#id", sync = true)
    public Student selectOrderById(String id) {
        Student byId = studentService.getById(id);
        System.out.println("db1查询来了==============");
        return byId;
    }

    @Override
    @CachePut(key = "#student.id")
    public Student updateOrderById(Student student) {
        int i = studentDao.updateById(student);
        System.out.println(i + "db2查询来了==============");
        return student;
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
