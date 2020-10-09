package com.cuterwrite.dbfinal.config;

import java.util.HashMap;
import java.util.Map;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cuterwrite.dbfinal.common.QueueEunm;



/**  
 * 消息队列配置
 * 这里采用延迟插件实现，配置交换机、队列和绑定关系
 * @author Pang S.Z.
 * @create 2020-10-09 11:25:47 
 */
@Configuration
public class RabbitMqConfig {
	/*
	 * 订单延迟插件消息队列绑定的交换机
	 */
	@Bean
	CustomExchange orderDirect() {
		Map<String, Object>args=new HashMap<>();
		args.put("x-delayed-type", "direct");
		return new CustomExchange(QueueEunm.QUEUE_ORDER_CANCEL.getExchange(), "x-delayed-message",true,false,args);
	}
	
	/*
	 * 订单延迟插件队列
	 */
	@Bean
	public Queue orderQueue() {
		Map<String, Object>args=new HashMap<>();
		args.put("x-queue-type", "classic");
		return new Queue(QueueEunm.QUEUE_ORDER_CANCEL.getName(),true, false, false, args);
	}
	
	/*
	 * 将订单延迟插件队列绑定到交换机
	 */
	@Bean
	public Binding orderBinding(CustomExchange orderDirect,Queue orderQueue) {
		return BindingBuilder
				.bind(orderQueue)
				.to(orderDirect)
				.with(QueueEunm.QUEUE_ORDER_CANCEL.getRouteKey())
				.noargs();
	}
}
