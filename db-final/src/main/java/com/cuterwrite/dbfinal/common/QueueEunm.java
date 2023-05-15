package com.cuterwrite.dbfinal.common;

import lombok.Getter;

/**
 * 消息队列枚举配置
 *
 * @author Pang S.Z.
 * @create 2020-10-09 11:18:16
 */
@Getter
public enum QueueEunm {
    /*
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("dbfinal.order.direct", "dbfinal.order.cancel", "dbfinal.order.cancel");

    /*
     * 交换名称
     */
    private String exchange;
    /*
     * 队列名称
     */
    private String name;
    /*
     * 路由键
     */
    private String routeKey;

    QueueEunm(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
