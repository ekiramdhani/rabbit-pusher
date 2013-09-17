package com.thinkbites.rabbitmqtest.pubsub;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import static com.thinkbites.rabbitmqtest.Constants.*;

public class AndroidPublisher {

	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(SERVER_URL);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        channel.exchangeDeclare(ANDROID_EXCHANGE_NAME, "fanout");

        
        long timeStart = System.currentTimeMillis();
        int messages = 2000;
        for (int i = 0; i < messages; i++) {
        	String message = ""+i;
        	channel.basicPublish(ANDROID_EXCHANGE_NAME, "", null, message.getBytes());
        	Thread.sleep(10);
		}
        
        channel.close();
        connection.close();
		System.out.println("Messages sent:"+messages+", time:"+(System.currentTimeMillis()-timeStart));
	}
	
}