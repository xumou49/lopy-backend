package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.lopy.dao.SeatingDao;
import com.lopy.entity.SeatingEntity;
import com.lopy.service.biz.intf.SeatingService;


@Service("seatingService")
public class SeatingServiceImpl extends ServiceImpl<SeatingDao, SeatingEntity> implements SeatingService {

}