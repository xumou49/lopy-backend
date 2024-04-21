package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.restaurant.RestaurantListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.restaurant.RestaurantVO;
import com.lopy.service.biz.intf.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Restaurant API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * Returns page of available restaurants in the system.
     * @param restaurantListDTO: query params
     * @return Restaurant List in Pagination
     */
    @PostMapping("/page")
    public RespVO<PageResult<RestaurantVO>> page(@RequestBody RestaurantListDTO restaurantListDTO) {
        PageResult<RestaurantVO> page = restaurantService.pageByQuery(restaurantListDTO);
        return RespVO.ok(page);
    }

    /**
     * Returns list of available restaurants in the system
     * @param restaurantListDTO: query params
     * @return Restaurant List
     */
    @PostMapping("/list")
    public RespVO<List<RestaurantVO>> list(@RequestBody RestaurantListDTO restaurantListDTO) {
        List<RestaurantVO> list = restaurantService.listByQuery(restaurantListDTO);
        return RespVO.ok(list);
    }

    @GetMapping("/info")
    public RespVO<RestaurantVO> info(@RequestParam Long id){
        RestaurantVO restaurantVO = restaurantService.getById(id);
        return RespVO.ok(restaurantVO);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody RestaurantListDTO restaurantListDTO){
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody RestaurantListDTO restaurantListDTO){
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids){
        return RespVO.ok();
    }

}
