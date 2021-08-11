package com.cp.sendmq.controller;


import com.cp.sendmq.entity.Order;
import com.cp.sendmq.entity.SysPersonalInfo;
import com.cp.sendmq.service.SendService;
import com.cp.sendmq.service.mq.RabbitSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/06/02
 */
@RestController
@RequestMapping("test")
@Api(tags = "rabbitmq测试")
public class TestController {

    @Autowired
    private SendService sendService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitSender rabbitSender;

    //@Autowired
    //private Sender sender;
    @GetMapping("/test1")
    @ApiOperation("test1测试")
    public String test1() {
        SysPersonalInfo sysPersonalInfo = new SysPersonalInfo();
        sysPersonalInfo.setId("9666");
        sysPersonalInfo.setPersonalName("pppp");
        sendService.send("SysPersonalInfoVO3", sysPersonalInfo);
        //sender.send("ddd","ffff");
        return "success";
    }

    @GetMapping("/test2")
    @ApiOperation("test2测试")
    public String test2() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息1234".getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.abc", message);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", "hello object message send!");
        return "success";
    }

    @GetMapping("/test3")
    @ApiOperation("test3测试")
    public String test3() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> properties = new HashMap<>();
        properties.put("number", "12345");
        properties.put("send_time", simpleDateFormat.format(new Date()));
        Order order = new Order();
        //order.setId("661");
        order.setName("订单消息");
        order.setContent("订单描述信息");
        rabbitSender.send2(order, properties);
        return "success";
    }
}
