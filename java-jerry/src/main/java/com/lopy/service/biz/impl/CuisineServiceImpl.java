package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.cuisine.CuisineDTO;
import com.lopy.common.vo.cuisine.CuisineVO;
import com.lopy.dao.CuisineDAO;
import com.lopy.entity.Cuisine;
import com.lopy.service.biz.intf.CuisineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("cuisineService")
public class CuisineServiceImpl extends ServiceImpl<CuisineDAO, Cuisine> implements CuisineService {

    @Value("${app.image-domain}")
    private String imageDomain;

    private CuisineVO toCuisineVO(Cuisine cuisine) {
        CuisineVO cuisineVO = new CuisineVO();
        BeanUtils.copyProperties(cuisine, cuisineVO);
        cuisineVO.setImageUrl(imageDomain + cuisine.getImagePath());
        return cuisineVO;
    }

    @Override
    public List<CuisineVO> listByQuery(CuisineDTO cuisineDTO) {
        return list().stream().map(this::toCuisineVO).collect(Collectors.toList());
    }
}