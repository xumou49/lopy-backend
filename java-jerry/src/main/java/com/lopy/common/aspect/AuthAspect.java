package com.lopy.common.aspect;


import com.lopy.common.auth.AuthContext;
import com.lopy.common.auth.Authorize;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.exception.PermissionDeniedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component
@Aspect
public class AuthAspect {

    @Pointcut("@annotation(com.lopy.common.auth.Authorize)")
    public void annotationExp() {}

    @Before("annotationExp()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // check if the user type match authorized type
        String userType = AuthContext.getUserType();
        if (Objects.equals(userType, CommonConstant.Account.ADMIN)) {
            return;
        }

        // must have the `Authorize` annotation when the method is executed
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Authorize authorize = method.getAnnotation(Authorize.class);
        for (String accessType: authorize.access()) {
            if (Objects.equals(accessType, userType)) {
                return;
            }
        }

        // TODO
        throw new PermissionDeniedException("error");
    }
}
