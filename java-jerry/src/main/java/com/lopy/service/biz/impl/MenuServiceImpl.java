package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.MenuDao;
import com.lopy.entity.Menu;
import com.lopy.service.biz.intf.MenuService;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

}