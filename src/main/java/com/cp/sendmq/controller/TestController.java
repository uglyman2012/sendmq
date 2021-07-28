package com.cp.sendmq.controller;


import com.cp.sendmq.bean.SysPersonalInfo;
import com.cp.sendmq.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String test() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/plain");
        Message message = new Message("mq 消息1234".getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.abc", message);

        rabbitTemplate.convertAndSend("topic001", "spring.amqp", "hello object message send!");
        return "success";
    }
}
