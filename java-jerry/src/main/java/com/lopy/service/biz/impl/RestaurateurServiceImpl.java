package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.RestaurateurDao;
import com.lopy.entity.RestaurateurEntity;
import com.lopy.service.biz.intf.RestaurateurService;
import org.springframework.stereotype.Service;

@Service("restaurateurService")
public class RestaurateurServiceImpl extends ServiceImpl<RestaurateurDao, RestaurateurEntity> implements RestaurateurService {

}