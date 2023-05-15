package com.cuterwrite.dbfinal.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 自定义WebLog日志过滤器
 *
 * @author Pang S.Z.
 * @create 2020-10-14 19:58:13
 */
public class WebLogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event.getMessage().startsWith("webLog")) {
            return FilterReply.ACCEPT;
        }
        return FilterReply.DENY;
    }

}
