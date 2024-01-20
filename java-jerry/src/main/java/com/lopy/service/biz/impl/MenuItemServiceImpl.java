package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.MenuItemDAO;
import com.lopy.entity.MenuItem;
import com.lopy.service.biz.intf.MenuItemService;
import org.springframework.stereotype.Service;

@Service("menuItemService")
public class MenuItemServiceImpl extends ServiceImpl<MenuItemDAO, MenuItem> implements MenuItemService {

}