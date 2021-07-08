package com.rain.rabbit.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author RainZero
 * @date 2021/7/8
 */
public class HelloProduce {
    private static final String HELLO_MQ = "hello_mq";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.200");
        factory.setUsername("admin");
        factory.setPassword("admin");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(HELLO_MQ, false, false, false, null);
            String message = "hello world";
            channel.basicPublish("", HELLO_MQ, null, message.getBytes());
            System.out.println("发送完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
