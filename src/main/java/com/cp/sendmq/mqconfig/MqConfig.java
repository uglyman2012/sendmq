package com.cp.sendmq.mqconfig;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.CompositeMessageChannelConfigurer;
import org.springframework.cloud.stream.binding.MessageChannelConfigurer;
import org.springframework.cloud.stream.binding.MessageConverterConfigurer;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.AbstractMessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBinding({Source.class})
public class MqConfig {

    @Bean
    public CompositeMessageChannelConfigurer compositeMessageChannelConfigurer(
            MessageConverterConfigurer messageConverterConfigurer) {
        List<MessageChannelConfigurer> configurers = new ArrayList<>();
        configurers.add(messageConverterConfigurer);
        configurers.add(outputChannelConfigure());
        return new CompositeMessageChannelConfigurer(configurers);
    }


    @Bean
    public MessageChannelConfigurer outputChannelConfigure() {
        return new MessageChannelConfigurer() {
            /**
             * 消息消费配置
             * @param messageChannel
             * @param channelName
             */
            @Override
            public void configureInputChannel(MessageChannel messageChannel, String channelName) {
                //待研究
                //if (channelName.equals(Source.OUTPUT)) {
                //    AbstractMessageChannel channel = (AbstractMessageChannel) messageChannel;
                //    channel.addInterceptor(new ChannelInterceptor() {
                //        @Override
                //        public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
                //            System.out.println(ex+"mmmmmmmmmmmmmmmmmm");
                //        }
                //    });
                //}
            }

            /**
             * 消息发送配置
             * @param messageChannel
             * @param channelName
             */
            @Override
            public void configureOutputChannel(MessageChannel messageChannel, String channelName) {
                if (channelName.equals(Source.OUTPUT)) {
                    AbstractMessageChannel channel = (AbstractMessageChannel) messageChannel;
                    channel.addInterceptor(new ChannelInterceptor() {
                        @Override
                        public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
                            System.out.println(sent + "kkkkkkkkkkkkkkkkkkkkkkkkkk");
                        }
                    });
                }
            }
        };
    }
}
