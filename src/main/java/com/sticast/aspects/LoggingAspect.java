package com.sticast.aspects;

import java.util.Arrays;
import java.util.logging.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.sticast.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.sticast.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.sticast.repository.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.sticast.validation.*.*(..))")
    private void forValidationPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() || forValidationPackage()")
    private void forAppFlow() {}

    //AOP expression for which methods shall be intercepted
    @Around("forAppFlow()")
    public Object profileAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        logger.info("Enter: " + className + "." + methodName + " with args " + Arrays.toString(joinPoint.getArgs()));

        final StopWatch stopWatch = new StopWatch();

        Object result;
        try {
            stopWatch.start();
            result = joinPoint.proceed();
            stopWatch.stop();

            logger.info("Execution time of " + className + "." + methodName + " : " + stopWatch.getTotalTimeMillis() + " ms");
            logger.info("Exit: " + result);
            return result;
        } catch (Throwable e) {
            logger.warning("Exception threw " + className + "." + methodName + ": " +e.getMessage());
            throw e;
        }
    }
}