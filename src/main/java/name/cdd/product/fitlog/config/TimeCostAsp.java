package name.cdd.product.fitlog.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component

@Aspect
public class TimeCostAsp {

    @Around("execution(* name.cdd.product.fitlog.service.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        TimeCost tc = new TimeCost().start();

        Object[] args = joinPoint.getArgs();
        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//        if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
//            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
//            args[args.length - 1] = entityManager;
//        }
//        logger.info("请求参数为{}",args);
        //动态修改其参数
        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
        //如果这里不返回result，则目标对象实际返回值会被置为null
        long timeCost = tc.stop().costMs();

        if(timeCost > 1000L){
            System.err.println("cdd warn: too much time cost invoking " + joinPoint.getSignature().getName() + ": " + timeCost + "ms.");
        } else {
            System.out.println("timeCost for invoking " + joinPoint.getSignature().getName() + ": " + timeCost + "ms.");
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
