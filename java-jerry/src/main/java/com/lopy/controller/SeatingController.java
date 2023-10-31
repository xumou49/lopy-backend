package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.Seating;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params){
        return RespVO.ok().put("page", "");
    }


    @RequestMapping("/info/{id}")
    public RespVO<Void> info(@PathVariable("id") Long id){
		Seating seating = seatingService.getById(id);

        return RespVO.ok().put("seating", seating);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody Seating seating){
		seatingService.save(seating);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody Seating seating){
		seatingService.updateById(seating);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		seatingService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
