package com.cuterwrite.dbfinal.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cuterwrite.dbfinal.common.QueueEunm;

/**
 * 取消订单信息发出者
 *
 * @author Pang S.Z.
 * @create 2020-10-09 11:41:09
 */
@Component
public class CancelOrderSender {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final Long delayTime) {
        amqpTemplate.convertAndSend(QueueEunm.QUEUE_ORDER_CANCEL.getExchange(), QueueEunm.QUEUE_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", delayTime);
                return message;
            }
        });
        LOGGER.info("send delay message orderId:{}", orderId);
    }
}
