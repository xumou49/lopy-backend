package com.lopy.service.biz.intf;

import com.lopy.common.dto.menu.MenuCategoryListDTO;
import com.lopy.common.vo.restaurant.MenuCategoryVO;

import java.util.List;

public interface MenuCategoryService {
    List<MenuCategoryVO> listByQuery(MenuCategoryListDTO menuCategoryListDTO);
    
}

