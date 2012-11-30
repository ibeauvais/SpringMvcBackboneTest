package fr.ib.test.springMvcTest.common.aspect;

import fr.ib.test.springMvcTest.common.log.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    @Around("execution(public * *(..)) && @annotation(logAnnotation)")
    public Object logMethod(ProceedingJoinPoint pjp, Log logAnnotation) throws Throwable {
        Logger logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String methodeName = "";
        methodeName = traceBeforeMethod(pjp, logger, methodeName);

        try {
            return pjp.proceed();
        } finally {
            traceAfterMethod(logger, methodeName);
        }


    }

    private void traceAfterMethod(Logger logger, String methodeName) {
        if (logger.isDebugEnabled())
            logger.debug("<< {}", methodeName);
    }

    private String traceBeforeMethod(ProceedingJoinPoint pjp, Logger logger, String methodeName) {
        if (logger.isDebugEnabled()) {
            methodeName = pjp.getSignature().getName();
            String args = Arrays.toString(pjp.getArgs());

            logger.debug(">> {} {}", methodeName, args);
        }
        return methodeName;
    }
}
