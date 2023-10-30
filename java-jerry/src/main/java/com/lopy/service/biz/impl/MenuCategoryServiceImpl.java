package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.MenuCategoryDao;
import com.lopy.entity.MenuCategoryEntity;
import com.lopy.service.biz.intf.MenuCategoryService;
import org.springframework.stereotype.Service;

@Service("menuCategoryService")
public class MenuCategoryServiceImpl extends ServiceImpl<MenuCategoryDao, MenuCategoryEntity> implements MenuCategoryService {

}