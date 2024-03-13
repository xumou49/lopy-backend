package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.menu.MenuCategoryDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.MenuCategoryQuery;
import com.lopy.common.query.OrderItemQuery;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.restaurant.MenuCategoryVO;
import com.lopy.dao.MenuCategoryDAO;
import com.lopy.entity.MenuCategory;
import com.lopy.service.biz.intf.MenuCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("menuCategoryService")
public class MenuCategoryServiceImpl extends ServiceImpl<MenuCategoryDAO, MenuCategory> implements MenuCategoryService {
    
    @Value("${app.image-domain}")
    private String imageDomain;
    
    private MenuCategoryVO toMenuCategoryVO(MenuCategory menuCategory) {
        MenuCategoryVO menuCategoryVO = new MenuCategoryVO();
        BeanUtils.copyProperties(menuCategory, menuCategoryVO);
        return menuCategoryVO;
    }
    
    @Override
    public List<MenuCategoryVO> listByQuery(MenuCategoryDTO menuCategoryDTO) {
        MenuCategoryQuery menuCategoryQuery = MenuCategoryQuery.build(menuCategoryDTO);
        return baseMapper.selectByQuery(menuCategoryQuery).stream().map(this::toMenuCategoryVO).collect(Collectors.toList());
    }
    
}