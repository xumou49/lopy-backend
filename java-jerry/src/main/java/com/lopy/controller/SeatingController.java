package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.restaurant.SeatingDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.Seating;
import com.lopy.service.biz.intf.SeatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/info")
    public RespVO<Seating> info(@RequestParam Long id) {
		Seating seating = seatingService.getById(id);
        return RespVO.ok(seating);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody SeatingDTO seatingDTO) {
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
