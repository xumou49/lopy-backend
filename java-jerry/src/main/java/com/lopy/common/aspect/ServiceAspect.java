package com.lopy.common.aspect;


import com.lopy.common.exception.PermissionDeniedException;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.utils.ExceptionUtil;
import com.lopy.common.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;

@Slf4j
@Component
@Aspect
public class ServiceAspect {

    /**
     * @description Handle raised exception from service component
     * @author Dex
     * @date 2023/09/21
     */
    @Around("execution(public * com.lopy.service..*Service*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ValidationException | ServiceException | PermissionDeniedException e) {
            logErrorMessage(joinPoint, e);
            throw e;
        } catch (Throwable e) {
            logErrorMessage(joinPoint, e);
            throw new ServiceException(MessageUtil.getMessage("error.internal.server.error"));
        }
    }

    private void logErrorMessage(ProceedingJoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String errorMsg = String.format("Exception raised when %s invokes %s method", className, methodName);
        log.error("{}", errorMsg);
        log.error("{}", ExceptionUtil.getStackTraceAsString(e));
    }
}
