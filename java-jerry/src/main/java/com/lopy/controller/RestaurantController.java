package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.Restaurant;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @GetMapping("/info/{id}")
    public RespVO<Void> info(@PathVariable("id") Long id){
		Restaurant restaurant = restaurantService.getById(id);

        return RespVO.ok().put("restaurant", restaurant);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody Restaurant restaurant){
		restaurantService.save(restaurant);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody Restaurant restaurant){
		restaurantService.updateById(restaurant);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		restaurantService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
