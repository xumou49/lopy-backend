package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.SeatingEntity;
import com.lopy.service.biz.intf.SeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/seating")
public class SeatingController {
    @Autowired
    private SeatingService seatingService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params){
        return RespVO.ok().put("page", "");
    }


    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		SeatingEntity seating = seatingService.getById(id);

        return RespVO.ok().put("seating", seating);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody SeatingEntity seating){
		seatingService.save(seating);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody SeatingEntity seating){
		seatingService.updateById(seating);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		seatingService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
