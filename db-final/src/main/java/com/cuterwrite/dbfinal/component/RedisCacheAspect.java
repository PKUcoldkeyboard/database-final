package com.cuterwrite.dbfinal.component;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Redis缓存切面，防止Redis宕机影响正常业务逻辑
 * 所谓切面，简单来说就是与业务无关，却为业务模块所共同调用的逻辑或责任封装起来
 *
 * @author Pang S.Z.
 * @create 2020-10-07 14:57:28
 */
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {
    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * com.cuterwrite.dbfinal.service.RedisService.*(..))")
    public void cacheAspect() {

    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
        }
        return result;
    }
}
