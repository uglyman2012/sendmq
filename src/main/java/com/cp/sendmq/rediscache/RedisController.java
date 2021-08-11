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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redisCache/test")
@Api(tags = "redisCache测试")
public class RedisController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/test1")
    @ApiOperation("test1测试")
    public String test3() {
        //Order order = new Order();
        //order.setId("99");
        //order.setContent("ppp");
        //order.setName("第一");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Order order = orderService.selectOrderById("007");
        Order order1 = orderService.updateOrderById("007");
        int a = 9 / 0;
        //String s = orderService.selectStringById("33");
        //Order order1 = orderService.selectOrderByparam(order);
        //redisTemplate.opsForValue().set("pp",order);
        //Order pp = (Order)redisTemplate.opsForValue().get("pp");
        return "success";
    }
}
