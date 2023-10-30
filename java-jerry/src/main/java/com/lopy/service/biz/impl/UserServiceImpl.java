package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.lopy.dao.UserDAO;
import com.lopy.entity.User;
import com.lopy.service.biz.intf.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {

}