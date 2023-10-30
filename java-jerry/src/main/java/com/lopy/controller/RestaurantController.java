package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.RestaurantEntity;
import com.lopy.service.biz.intf.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @GetMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		RestaurantEntity restaurant = restaurantService.getById(id);

        return RespVO.ok().put("restaurant", restaurant);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody RestaurantEntity restaurant){
		restaurantService.save(restaurant);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody RestaurantEntity restaurant){
		restaurantService.updateById(restaurant);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		restaurantService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
