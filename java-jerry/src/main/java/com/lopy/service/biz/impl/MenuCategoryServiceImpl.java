package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.menu.MenuCategoryListDTO;
import com.lopy.common.query.MenuCategoryQuery;
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
    public List<MenuCategoryVO> listByQuery(MenuCategoryListDTO menuCategoryListDTO) {
        MenuCategoryQuery menuCategoryQuery = MenuCategoryQuery.build(menuCategoryListDTO);
        return baseMapper.selectByQuery(menuCategoryQuery).stream().map(this::toMenuCategoryVO).collect(Collectors.toList());
    }
    
}