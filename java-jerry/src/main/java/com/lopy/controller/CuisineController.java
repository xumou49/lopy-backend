package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.cuisine.CuisineDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.cuisine.CuisineVO;
import com.lopy.service.biz.intf.CuisineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Cuisine API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/cuisine")
public class CuisineController {

    @Autowired
    private CuisineService cuisineServiceImpl;

    @PostMapping("/list")
    public RespVO<List<CuisineVO>> list(@RequestBody CuisineDTO cuisineDTO) {
        List<CuisineVO> list = cuisineServiceImpl.listByQuery(cuisineDTO);
        return RespVO.ok(list);
    }
}
