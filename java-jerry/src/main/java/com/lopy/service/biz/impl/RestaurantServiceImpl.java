package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.restaurant.RestaurantListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.RestaurantQuery;
import com.lopy.common.utils.CollectionUtil;
import com.lopy.common.utils.DateUtil;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.restaurant.MenuCategoryVO;
import com.lopy.common.vo.restaurant.MenuItemVO;
import com.lopy.common.vo.restaurant.MenuVO;
import com.lopy.common.vo.restaurant.RestaurantVO;
import com.lopy.dao.MenuCategoryDAO;
import com.lopy.dao.RestaurantDAO;
import com.lopy.entity.Menu;
import com.lopy.entity.MenuCategory;
import com.lopy.entity.MenuItem;
import com.lopy.entity.Restaurant;
import com.lopy.service.biz.intf.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service("restaurantService")
public class RestaurantServiceImpl extends ServiceImpl<RestaurantDAO, Restaurant> implements RestaurantService {

    @Value("${app.image-domain}")
    private String imageDomain;

    @Autowired
    private MenuCategoryDAO menuCategoryDAO;

    private RestaurantVO toRestaurantVO(Restaurant restaurant) {
        RestaurantVO restaurantVO = new RestaurantVO();
        BeanUtils.copyProperties(restaurant, restaurantVO);
        restaurantVO.setImageUrl(imageDomain + restaurant.getImagePath());
        return restaurantVO;
    }

    private MenuCategoryVO toMenuCategoryVO(MenuCategory menuCategory) {
        MenuCategoryVO menuCategoryVO = new MenuCategoryVO();
        BeanUtils.copyProperties(menuCategory, menuCategoryVO);
        return menuCategoryVO;
    }

    private MenuVO toMenuVO(Menu menu) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menu, menuVO);
        return menuVO;
    }

    private MenuItemVO toMenuItemVO(MenuItem menuItem) {
        MenuItemVO menuItemVO = new MenuItemVO();
        BeanUtils.copyProperties(menuItem, menuItemVO);
        menuItemVO.setImageUrl(imageDomain + menuItem.getImagePath());
        return menuItemVO;
    }

    @Override
    public List<RestaurantVO> listByQuery(RestaurantListDTO restaurantListDTO) {
        // if search for restaurant with promotion, find those which has promotion within today
        if (restaurantListDTO.getPromotionSearch()) {
            LocalDateTime now = LocalDateTime.now();
            String startDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0).format(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME));
            String endDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59).format(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME));
            List<Restaurant> list = baseMapper.selectByPromotion(startDate, endDate);
            return list.stream().map(this::toRestaurantVO).collect(Collectors.toList());
        }

        // search restaurant with high highest rank (todo)
        SearchPage searchPage = restaurantListDTO.getSearchPage();
        RestaurantQuery restaurantQuery = RestaurantQuery.build(restaurantListDTO);
        return baseMapper.selectByQuery(restaurantQuery).stream().map(this::toRestaurantVO).collect(Collectors.toList());
    }

    public PageResult<RestaurantVO> pageByQuery(RestaurantListDTO restaurantListDTO) {
        SearchPage searchPage = restaurantListDTO.getSearchPage();
        RestaurantQuery restaurantQuery = RestaurantQuery.build(restaurantListDTO);
        // query with pagination
        Page<Restaurant> page = new Page<>(searchPage.getPage(), searchPage.getPageSize());
        PageResult<Restaurant> pageResult = PaginationUtil.buildPageResult(baseMapper.selectByPageAndQuery(page, restaurantQuery), searchPage);
        return PaginationUtil.changePageResult(pageResult, this::toRestaurantVO);
    }

    @Override
    public RestaurantVO getById(Long id) {
        RestaurantVO restaurantVO = toRestaurantVO(baseMapper.selectById(id));
        List<MenuCategoryVO> menuCategoryList = baseMapper.selectCategoryByRestaurantId(id).stream().map(this::toMenuCategoryVO).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(menuCategoryList)) {
            return restaurantVO;
        }

        MenuCategoryVO mc = menuCategoryList.get(0);
        List<MenuVO> menuList = baseMapper.selectMenuByCategoryId(mc.getId()).stream().map(this::toMenuVO).collect(Collectors.toList());

        for (int j = 0; j < menuList.size(); j++) {
            MenuVO m = menuList.get(j);
            List<MenuItemVO> menuItemList = baseMapper.selectMenuItemByMenuId(m.getId()).stream().map(this::toMenuItemVO).collect(Collectors.toList());
            m.setMenuItemList(menuItemList);
        }
        mc.setMenuList(menuList);
        restaurantVO.setMenuCategory(mc);
        
        return restaurantVO;
    }

    public RestaurantVO getByIdV2(Long id) {
        RestaurantVO restaurantVO = toRestaurantVO(baseMapper.selectById(id));
        List<MenuCategory> menuCategories = menuCategoryDAO.selectByRestaurantIdAndStatus(id, CommonConstant.STATUS_ENABLE);
        if (CollectionUtil.isEmpty(menuCategories)) {
            return restaurantVO;
        }

        // should only have one active menu category at any time
        MenuCategory menuCategory = menuCategories.get(0);
        MenuCategoryVO menuCategoryVO = baseMapper.selectAndBuildMenuCategoryVO(restaurantVO.getId(), menuCategory.getId());

        // update the image path of menuItem
        if (menuCategoryVO != null) {
            List<MenuVO> menuList = menuCategoryVO.getMenuList();
            if (CollectionUtil.isNotEmpty(menuList)) {
                menuList.forEach(menu -> menu.getMenuItemList().forEach(item -> item.setImageUrl(imageDomain + item.getImageUrl())));
            }
        }

        restaurantVO.setMenuCategory(menuCategoryVO);
        return restaurantVO;
    }
}