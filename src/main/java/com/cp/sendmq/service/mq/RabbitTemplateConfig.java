//package com.cp.sendmq.service.mq;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * <p>
// *
// * </p>
// *
// * @author yang
// * @since 2021/07/30
// */
//@Component
//public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setConfirmCallback(this);            //指定 ConfirmCallback
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("消息唯一标识：" + correlationData);
//        System.out.println("确认结果：" + ack);
//        System.out.println("失败原因：" + cause);
//
//    }
//}
