package com.cp.sendmq.rediscache;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */

import com.cp.sendmq.entity.Order;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redisCache/test")
@Api(tags = "redisCache测试")
public class RedisController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StudentService studentService;

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    public String test1() {
        //Order order = new Order();
        //order.setId("99");
        //order.setContent("ppp");
        //order.setName("第一");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //RMap<String, String> kk = redissonClient.getMap("kk");
        //kk.put("hh","ggg");
        //kk.expire(40,TimeUnit.SECONDS);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 1; i < 2; i++) {
            String a = i + "";
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Order order = orderService.selectOrderById("p1");
                    System.out.println("第几次查询" + order.toString());
                    //Order order1 = orderService.updateOrderById("p1");
                    //System.out.println("第u次查询"+order1.toString());
                }
            });
        }


        //String s = orderService.selectStringById("33");
        //Order order1 = orderService.selectOrderByparam(order);
        //redisTemplate.opsForValue().set("pp",order);
        //Order pp = (Order)redisTemplate.opsForValue().get("pp");
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test2")
    @ApiOperation("test2测试")
    public String test2() {
        //Order order = new Order();
        Student student = new Student();
        student.setId("02");
        student.setSex("男");
        boolean b = studentService.updateById(student);
        //int a = 9 / 0;
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test3")
    @ApiOperation("test3测试")
    public String test3() {
        //Order order = new Order();
        Student student = new Student();
        student.setId("01");
        student.setSex("男");
        //boolean b = studentService.deleteByid("01");
        //boolean b = studentService.deleteBach("01");
        //模糊查询
        Set<String> keys = redisTemplate.keys("catalog_test_id::" + "*");
        System.out.println(keys.toString());
        return "success";
    }
}
