package com.rain.rabbit.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author RainZero
 * @date 2021/7/8
 */
public class HelloConsumer {
    private static final String HELLO_MQ = "hello_mq";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.200");
        factory.setUsername("admin");
        factory.setPassword("admin");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println(new String(message.getBody()));
            };
            CancelCallback cancelCallback = (consumerTag) -> {
                System.out.println(" 消息消费被中断");
            };

            channel.basicConsume(HELLO_MQ, true, deliverCallback, cancelCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
