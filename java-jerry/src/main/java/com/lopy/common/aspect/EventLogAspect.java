package com.lopy.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lopy.common.auth.AuthContext;
import com.lopy.common.utils.WebUtil;
import com.lopy.dao.EventLogDAO;
import com.lopy.entity.EventLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@Aspect
public class EventLogAspect {

    @Autowired
    private EventLogDAO eventLogDAO;

    /**
     * @description Log the request information
     * @author Dex
     * @date 2023/09/24
     */
    @Around("execution(public * com.lopy.controller..*Controller*.*(..))")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        doUserLog();
        return proceed;
    }

    @Transactional(rollbackFor = Exception.class)
    public void doUserLog() {
        HttpServletRequest request = WebUtil.getRequest();
        // skip GET & POST method
        if (!Objects.equals(request.getMethod(), "PUT") && !Objects.equals(request.getMethod(), "DELETE")) {
            return;
        }

        JSONObject requestMetaData = JSON.parseObject(WebUtil.getRequestMetaData());
        EventLog eventLog = new EventLog();
        eventLog.setUserId(AuthContext.getUserId());
        eventLog.setRequestUrl(request.getRequestURI());
        eventLog.setRequestMethod(request.getMethod());
        if (requestMetaData != null) {
            eventLog.setRequestArgs(requestMetaData.getString("args"));
            eventLog.setRequestBody(requestMetaData.getString("body"));
        }
        eventLog.setLogTime(new Date());
        eventLogDAO.insert(eventLog);
    }
}