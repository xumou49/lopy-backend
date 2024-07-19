package com.lopy.service.biz.impl;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.auth.LoginDTO;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.exception.ValidationException;
import com.lopy.common.pojo.OAuth;
import com.lopy.common.utils.JWTUtil;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.utils.StringUtil;
import com.lopy.common.utils.WebUtil;
import com.lopy.dao.AccessLogDAO;
import com.lopy.dao.UserDAO;
import com.lopy.entity.AccessLog;
import com.lopy.entity.User;
import com.lopy.service.biz.intf.AppleService;
import com.lopy.service.biz.intf.AuthService;
import com.lopy.service.biz.intf.CustomerService;
import com.lopy.service.google.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppleService appleServiceImpl;

    @Autowired
    private GoogleService googleServiceImpl;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccessLogDAO accessLogDAO;

    @Override
    public String phoneLogin(LoginDTO loginDTO) {
        return createJWT(new User());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String googleLogin(LoginDTO loginDTO) {
        OAuth oauth = googleServiceImpl.verifyOauthToken(loginDTO.getToken());
        log.info("auth: {}", oauth);
        User user = handleOauthLogin(oauth, CommonConstant.Account.PLATFORM_GOOGLE);
        String jwt = createJWT(user);
        saveAccessLog(user);
        return jwt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String appleLogin(LoginDTO loginDTO) {
        OAuth oauth = appleServiceImpl.verifyOauthToken(loginDTO.getToken());
        log.info("auth: {}", oauth);
        User user = handleOauthLogin(oauth, CommonConstant.Account.PLATFORM_APPLE);
        String jwt = createJWT(user);
        saveAccessLog(user);
        return jwt;
    }

    private void saveAccessLog(User user) {
        // get remote ip
        String remoteIp = "";
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            remoteIp = WebUtil.getIpAddr(servletRequestAttributes.getRequest());
        }
        AccessLog accessLog = new AccessLog();
        accessLog.setUserId(user.getId());
        accessLog.setUserAgent(WebUtil.getUserAgent());
        accessLog.setAccessTime(new Date());
        accessLog.setRemoteIp(remoteIp);
        accessLogDAO.insert(accessLog);
    }

    private User handleOauthLogin(OAuth oauth, int platform) {
        String openId = oauth.getId();
        String email = oauth.getEmail();
        if (StringUtil.isBlank(openId)) {
            throw new ValidationException(MessageUtil.getMessage(""));
        }

        // check if the user exists
        User user = userDAO.getByOpenIdAndPlatform(openId, platform);
        if (user == null) {
            // if is not found, it is first time login, simply create a new user
            user = new User();
            user.setType(CommonConstant.Account.CUSTOMER);
            user.setEmail(email);
            user.setOpenId(openId);
            user.setLoginPlatform(platform);
            user.setDevice(AuthContext.getUserDevice());
            user.setLocale(AuthContext.getUserLocale().toString());
            userDAO.insert(user);
            customerService.create(user);
        }
        return user;
    }

    private String createJWT(User user) {
        Map<String, String> claims = Map.of("id", String.valueOf(user.getId()), "type", user.getType());
        String token = JWTUtil.createToken(claims);
        if (StringUtil.isBlank(token)) {
            throw new ServiceException(MessageUtil.getMessage("error.token.create"));
        }
        return token;
    }
}
