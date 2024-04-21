package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.restaurant.SeatingListDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.Seating;
import com.lopy.service.biz.intf.SeatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Seating API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/seating")
public class SeatingController {

    @Autowired
    private SeatingService seatingService;

    @PostMapping("/list")
    public RespVO<Void> list() {
        return RespVO.ok();
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody SeatingListDTO seatingDTO) {
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody Seating seatingDTO) {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids) {
        return RespVO.ok();
    }

}
