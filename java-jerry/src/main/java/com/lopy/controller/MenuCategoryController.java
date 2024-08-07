package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.menu.MenuCategoryListDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.restaurant.MenuCategoryVO;
import com.lopy.service.biz.intf.MenuCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Menu Category API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/menu-category")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryService menuCategoryService;
    
    /**
     * Returns list of available menu category in the system
     *
     * @param menuCategoryListDTO: query params
     * @return MenuCategory List
     */
    @PostMapping("/list")
    public RespVO<List<MenuCategoryVO>> list(@RequestBody MenuCategoryListDTO menuCategoryListDTO) {
        List<MenuCategoryVO> list = menuCategoryService.listByQuery(menuCategoryListDTO);
        return RespVO.ok(list);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody MenuCategoryListDTO menuCategoryListDTO) {
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody MenuCategoryListDTO menuCategoryListDTO) {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids){
		// menuCategoryService.removeByIds(ids);
        return RespVO.ok();
    }
}
