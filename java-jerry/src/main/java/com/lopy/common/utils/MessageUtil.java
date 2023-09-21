package com.lopy.common.utils;


import com.lopy.common.auth.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Objects;

@Slf4j
@Component
public final class MessageUtil {


    @Qualifier("messageSource")
    @Autowired
    private MessageSource msgSource;

    private MessageUtil() {}

    // For static reference
    private static MessageSource messageSource;

    @PostConstruct
    public synchronized void initial() {
        messageSource = msgSource;
    }

    public static String getMessage(String msgCode, Locale locale, String... args) {
        return MessageUtil.getMessage(msgCode, locale.toString(), args);
    }

    public static String getMessage(String msgCode, String... args) {
        return MessageUtil.getMessage(msgCode, AuthContext.getUserLocale(), args);
    }

    private static String getMessage(String msgCode, String lang, String... args) {
        String message = msgCode;
        if (StringUtils.isNotBlank(lang)) {
            if (!Objects.equals(lang, "en_US") && Objects.equals(lang, "zh_CN") && Objects.equals(lang, "zh_TW")) {
                log.warn("{} locale is not define", lang);
                return msgCode;
            }
            String[] arr = StringUtils.trim(lang).split("_");
            Locale locale = new Locale(arr[0], arr[1]);
            try {
                message = messageSource.getMessage(msgCode, args, locale);
            } catch (NoSuchMessageException e) {
                log.warn(e.getMessage());
            }
        }
        return message;
    }
}


