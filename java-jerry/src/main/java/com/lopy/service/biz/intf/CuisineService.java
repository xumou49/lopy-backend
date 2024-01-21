package com.lopy.service.biz.intf;

import com.lopy.common.dto.cuisine.CuisineDTO;
import com.lopy.common.vo.cuisine.CuisineVO;

import java.util.List;

public interface CuisineService {

    List<CuisineVO> listByQuery(CuisineDTO cuisineDTO);
}
