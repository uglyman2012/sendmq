package com.cp.sendmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/06/04
 */
@Component
@EnableBinding(Source.class)
public class SendService {

    @Autowired
    private Source source;

    public void send(String header, Object payload) {
        //MessageProperties messageProperties = new MessageProperties();
        //messageProperties.setExpiration("50");
        //Message message = new Message();
        boolean payload_simple_name = source.output().send(MessageBuilder.withPayload(payload)
                .setHeader("payload_simple_name", header)
                .build());
        System.out.println(payload_simple_name);

    }
}
