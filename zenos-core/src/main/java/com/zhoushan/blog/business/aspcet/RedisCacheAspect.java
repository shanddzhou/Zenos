package com.zhoushan.blog.business.aspcet;

import com.zhoushan.blog.business.annotation.RedisCache;
import com.zhoushan.blog.business.service.RedisService;
import com.zhoushan.blog.utils.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhooo
 * @version V1.0
 * @Title: RedisCacheAspect
 * @Package com.zhoushan.blog.business.aspcet
 * @Description: Redis缓存切面
 * @date 7/12/2019 5:30 PM
 */

@Slf4j
@Aspect
@Component
public class RedisCacheAspect {
    private static final String BIZ_CACHE_PREFIX = "biz_cache_";
    @Autowired
    private RedisService redisService;

    @Pointcut(value = "@annotation(com.zhoushan.blog.business.annotation.RedisCache)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {

        Method method = AspectUtil.INSTANCE.getMethod(point);
        RedisCache redisCache = method.getAnnotation(RedisCache.class);
        boolean enable = redisCache.enable();
        if (!enable) {
            return point.proceed();
        }
        boolean flush = redisCache.flush();
        if (flush) {
            String key = AspectUtil.INSTANCE.getKey(point, BIZ_CACHE_PREFIX);
            log.info("清空缓存 - {}*", key);
            redisService.delBatch(key);
            return point.proceed();
        }
        String key = AspectUtil.INSTANCE.getKey(point, redisCache.key(), BIZ_CACHE_PREFIX);
        boolean hasKey = redisService.hasKey(key);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", key);
                return redisService.get(key);
            } catch (Exception e) {
                log.error("从缓存获取数据失败~", e);
            }
        }
        //先执行业务
        Object result = point.proceed();
        redisService.set(key, result, redisCache.expire(), redisCache.unit());
        log.info("{}从数据库中获取数据", key);
        return result;
    }
}
