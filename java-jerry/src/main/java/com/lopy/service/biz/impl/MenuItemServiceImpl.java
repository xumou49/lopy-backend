package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.MenuItemDao;
import com.lopy.entity.MenuItemEntity;
import com.lopy.service.biz.intf.MenuItemService;
import org.springframework.stereotype.Service;

@Service("menuItemService")
public class MenuItemServiceImpl extends ServiceImpl<MenuItemDao, MenuItemEntity> implements MenuItemService {

}