package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.restaurant.RestaurantDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.pagination.SearchPage;
import com.lopy.common.query.RestaurantQuery;
import com.lopy.common.utils.DateUtil;
import com.lopy.common.utils.PaginationUtil;
import com.lopy.common.vo.restaurant.RestaurantVO;
import com.lopy.dao.RestaurantDAO;
import com.lopy.entity.Restaurant;
import com.lopy.service.biz.intf.RestaurantService;
import org.springframework.beans.BeanUtils;
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

    private RestaurantVO toRestaurantVO(Restaurant restaurant) {
        RestaurantVO restaurantVO = new RestaurantVO();
        BeanUtils.copyProperties(restaurant, restaurantVO);
        restaurantVO.setImageUrl(imageDomain + restaurant.getImagePath());
        return restaurantVO;
    }

    @Override
    public List<RestaurantVO> listByQuery(RestaurantDTO restaurantDTO) {
        // if search for restaurant with promotion, find those which has promotion within today
        if (restaurantDTO.getPromotionSearch()) {
            LocalDateTime now = LocalDateTime.now();
            String startDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0).format(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME));
            String endDate = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59).format(DateTimeFormatter.ofPattern(DateUtil.DATE_TIME));
            List<Restaurant> list = baseMapper.selectByPromotion(startDate, endDate);
            return list.stream().map(this::toRestaurantVO).collect(Collectors.toList());
        }

        // search restaurant with high highest rank (todo)
        SearchPage searchPage = restaurantDTO.getSearchPage();
        RestaurantQuery restaurantQuery = new RestaurantQuery();
        restaurantQuery.setName(searchPage.getKeyword());
        restaurantQuery.setCuisine(restaurantDTO.getCuisine());
        return baseMapper.selectByQuery(restaurantQuery).stream().map(this::toRestaurantVO).collect(Collectors.toList());
    }

    public PageResult<RestaurantVO> pageByQuery(SearchPage searchPage) {
        RestaurantQuery restaurantQuery = new RestaurantQuery();
        restaurantQuery.setName(searchPage.getKeyword());
        // query with pagination
        Page<Restaurant> page = new Page<>(searchPage.getPage(), searchPage.getPageSize());
        PageResult<Restaurant> pageResult = PaginationUtil.buildPageResult(baseMapper.selectByPageAndQuery(page, restaurantQuery), searchPage);
        return PaginationUtil.changePageResult(pageResult, this::toRestaurantVO);
    }

    @Override
    public RestaurantVO getById(Long id) {
        return toRestaurantVO(super.getById(id));
    }
}