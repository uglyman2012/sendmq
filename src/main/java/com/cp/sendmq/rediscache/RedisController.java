package com.cp.sendmq.rediscache;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.cp.sendmq.entity.Order;
import com.cp.sendmq.entity.Student;
import com.cp.sendmq.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redisCache/test")
@Api(tags = "redisCache测试")
public class RedisController {

    private final Logger log = LoggerFactory.getLogger(RedisController.class);
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private StudentService studentService;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    //@Transactional(rollbackFor = Exception.class)
    public Student test1() {
        //Order order = new Order();
        //order.setId("99");
        //order.setContent("ppp");
        //order.setName("第一");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //RMap<String, String> kk = redissonClient.getMap("kk");
        //kk.put("hh","ggg");
        //kk.expire(40,TimeUnit.SECONDS);
        String idStr = IdWorker.getIdStr();
        //IdWorker.initSequence();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        //for (int i = 1; i < 2; i++) {
        //    String a = i + "";
        //    threadPoolExecutor.execute(new Runnable() {
        //        @Override
        //        public void run() {
        Student student3 = orderService.selectOrderById("19");
        log.info("P" + "第几次查询" + student3.toString());

        //        }
        //    });
        //}
        Student student = new Student();
        //student.setId("19");
        //student.setSex("nv1");
        //Student student1 = orderService.updateOrderById(student);
        //System.out.println("更改"+student1.toString());
        //String s = orderService.selectStringById("33");
        //Order order1 = orderService.selectOrderByparam(order);
        //redisTemplate.opsForValue().set("pp",order);
        //Order pp = (Order)redisTemplate.opsForValue().get("pp");
        return student;
    }

    @GetMapping("/test111")
    @ApiOperation("test111测试")
    //@Transactional(rollbackFor = Exception.class)
    public Student test111() {

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
                    Order order = new Order();
                    order.setId("99");
                    order.setContent("ppp");
                    order.setName("第一");
                    Order order1 = orderService.selectOrderByparam(order);
                    log.info(a + "第几次查询" + order1.toString());

                }
            });
        }
        Student student = new Student();
        //student.setId("19");
        //student.setSex("nv1");
        //Student student1 = orderService.updateOrderById(student);
        //System.out.println("更改"+student1.toString());
        //String s = orderService.selectStringById("33");
        //Order order1 = orderService.selectOrderByparam(order);
        //redisTemplate.opsForValue().set("pp",order);
        //Order pp = (Order)redisTemplate.opsForValue().get("pp");
        return student;
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
        log.info(keys.toString());
        return "success";
    }

    @GetMapping("/test4")
    @ApiOperation("test4测试")
    public String test4() {
        Student student = new Student();
        student.setName("paopao");
        student.setId("19");
        student.setSex("nv");
        LocalDateTime now = LocalDateTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(new Date());
        student.setAge(now);
        boolean save = studentService.save(student);
        return "success";
    }
}
