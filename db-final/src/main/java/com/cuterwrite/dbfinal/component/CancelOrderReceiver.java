package com.cuterwrite.dbfinal.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**  
 * 取消订单信息处理者
 * @author Pang S.Z.
 * @create 2020-10-09 11:45:40 
 */
@Component
@RabbitListener(queues = "dbfinal.order.cancel")
public class CancelOrderReceiver {
	private static Logger LOGGER=LoggerFactory.getLogger(CancelOrderReceiver.class);
	//todo,@autowire someService
	@RabbitHandler
	public void handle(Long orderId) {
		LOGGER.info("receive delay message orderId:{}",orderId);
		//取消订单方法
	}
}
