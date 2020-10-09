package com.cuterwrite.dbfinal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cuterwrite.dbfinal.common.QueueEunm;

/**  
 * 消息队列配置
 * 用于配置交换机、队列及队列与交换机的绑定关系
 * @author Pang S.Z.
 * @create 2020-10-09 11:25:47 
 */
@Configuration
public class RabbitMqConfig {
	/*
	 * 订单消息实际消费队列绑定交换机
	 */
	@Bean
	DirectExchange orderDirect() {
		return (DirectExchange)ExchangeBuilder
				.directExchange(QueueEunm.QUEUE_ORDER_CANCEL.getExchange())
				.durable(true)
				.build();
	}
	/*
	 * 订单延迟队列绑定的交换机
	 */
	@Bean
	DirectExchange orderTtlDirect() {
		return (DirectExchange)ExchangeBuilder
				.directExchange(QueueEunm.QUEUE_TTL_ORDER_CANCEL.getExchange())
				.durable(true)
				.build();
	}
	/*
	 * 订单实际消费队列
	 */
	@Bean
	public Queue orderQueue() {
		return new Queue(QueueEunm.QUEUE_ORDER_CANCEL.getName());
	}
	
	/*
	 * 订单延迟队列
	 */
	@Bean
	public Queue orderTtlQueue() {
		return new Queue(QueueEunm.QUEUE_TTL_ORDER_CANCEL.getName());
	}
	/*
	 * 订单队列绑定到交换机
	 */
	@Bean
	Binding orderBinding(DirectExchange orderDirect,Queue orderQueue) {
		return BindingBuilder
				.bind(orderQueue)
				.to(orderDirect)
				.with(QueueEunm.QUEUE_ORDER_CANCEL.getRouteKey());
	}
	/*
	 * 订单延迟队列绑定到交换机
	 */
	@Bean
	Binding orderTtlBinding(DirectExchange orderTtlDirect,Queue orderTtlQueue) {
		return BindingBuilder
				.bind(orderTtlQueue)
				.to(orderTtlDirect)
				.with(QueueEunm.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
	}
}
