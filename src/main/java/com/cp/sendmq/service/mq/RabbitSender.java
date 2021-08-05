package com.cp.sendmq.service.mq;

import com.cp.sendmq.bean.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/07/30
 */
@Component
public class RabbitSender {

    //确保消息能够从生产者发送到交换机 ，无论消息发送是否成功都执行一个回调方法
    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if (!ack) {
                //可以进行日志记录、异常处理、补偿处理等
                System.err.println("异常处理....");
            } else {
                //更新数据库，可靠性投递机制
            }
        }
    };
    //确保消息从交换机发送到队列，在发送失败的情况下Exchange有两种处理失败消息的模式，一种直接丢弃失败消息（默认是此种模式处理），一种将失败消息发送给ReturnCallBack
    //回调函数: return返回
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };
    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息方法调用: 构建Message消息
    public void send(Object message, Map<String, Object> properties) throws Exception {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, mhs);
        //MessageProperties messageProperties = new MessageProperties();
        //messageProperties.setContentType("application/json");

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 全局唯一  用于ack保证唯一一条消息,这边做测试写死一个。但是在做补偿策略的时候，必须保证这是全局唯一的消息
        CorrelationData correlationData = new CorrelationData("12345678901");
        rabbitTemplate.convertAndSend("topic003", "springboot.abc", msg, correlationData);
    }

    public void send2(Order message, Map<String, Object> properties) throws Exception {
        MessageHeaders mhs = new MessageHeaders(properties);
        //String s = JSONObject.toJSONString(message);
        //Message msg = MessageBuilder.createMessage(s, mhs);
        //org.springframework.amqp.core.Message build = org.springframework.amqp.core.MessageBuilder.withBody(s.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        //id + 时间戳 全局唯一  用于ack保证唯一一条消息,这边做测试写死一个。但是在做补偿策略的时候，必须保证这是全局唯一的消息
        CorrelationData correlationData = new CorrelationData("12345678901");
        rabbitTemplate.convertAndSend("topic002", "springboot.abc", message, correlationData);
    }

}
