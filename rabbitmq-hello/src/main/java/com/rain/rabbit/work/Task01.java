package com.rain.rabbit.work;

import com.rabbitmq.client.Channel;
import com.rain.rabbit.utils.RabbitMqUtils;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 *
 * @author RainZero
 * @date 2021/7/8
 */
public class Task01 {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        try (Channel channel = RabbitMqUtils.getChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入消息：");
            while (scanner.hasNext()) {
                String message = scanner.next();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" 发送消息完成:" + message);
            }
        }
    }
}
