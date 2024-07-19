package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.AccessLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccessLogDAO extends BaseMapper<AccessLog> {
}
