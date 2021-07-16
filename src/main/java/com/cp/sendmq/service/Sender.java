//package com.cp.sendmq.service;
//
//import org.springframework.cloud.stream.messaging.Processor;
//import org.springframework.integration.annotation.Publisher;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Sender {
//    //@Publisher需集合Mqconfig使用
//    @Publisher(Processor.OUTPUT)
//    public Message<Object> send(String header, Object payload) {
//        return MessageBuilder.withPayload(payload)
//                .setHeader("payload_simple_name", header)
//                .build();
//    }
//}
