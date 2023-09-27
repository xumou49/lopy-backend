package com.lopy.common.auth;

import com.lopy.common.constant.AuthConstant;
import com.lopy.common.utils.DataUtil;
import com.lopy.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
public class AuthContext {

    private AuthContext() { }

    private static String getRequestAttribute(String attributeName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return StringUtil.trim(request.getAttribute(attributeName));
        }
        return null;
    }

    public static Long getUserId() {
        return DataUtil.toLong(getRequestAttribute(AuthConstant.CURRENT_USER_HEADER));
    }

    public static String getUserType() {
        return getRequestAttribute(AuthConstant.CURRENT_USER_TYPE_HEADER);
    }

    public static String getToken() {
        return getRequestAttribute(AuthConstant.CURRENT_AUTH_TOKEN_HEADER);
    }

    public static Locale getUserLocale() {
        String lang = getRequestAttribute(AuthConstant.CURRENT_USER_LOCALE_HEADER);
        if (!StringUtil.isBlank(lang)) {
            String[] args = StringUtil.trim(lang).split("_");
            return new Locale(args[0], args[1]);
        }
        return Locale.US;
    }
}
