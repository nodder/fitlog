package name.cdd.product.fitlog.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component

@Aspect
public class TimeCostAsp {
    private static final Logger logger = LoggerFactory.getLogger(TimeCostAsp.class);

    @Around("execution(* name.cdd.product.fitlog.service.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        TimeCost tc = new TimeCost().start();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed(args);

        long timeCost = tc.stop().costMs();

        if(timeCost > 1000L){
            logger.warn("cdd warn: too much time cost invoking " + joinPoint.getSignature().getName() + ": " + timeCost + "ms.");
        } else {
            logger.info("timeCost for invoking " + joinPoint.getSignature().getName() + ": " + timeCost + "ms.");
        }

        return result;
    }

    class TimeCost {
        private long startTime;
        private long stopTime;

        public TimeCost start() {
            startTime = System.currentTimeMillis();
            return this;
        }

        public TimeCost stop() {
            stopTime = System.currentTimeMillis();
            return this;
        }

        public long costMs() {
            return stopTime - startTime;
        }
    }
}
