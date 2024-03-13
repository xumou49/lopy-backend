package com.lopy.common.vo.restaurant;

import com.lopy.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author Yao Liang
 * @created 05/03/2024 - 8:50 pm
 * @projct java-jerry
 */
@Data
public class MenuCategoryVO {
    private Long id;
    private Long restaurantId;
    private String categoryName;
    private Integer status;
    private List<MenuVO> menuList;
}
