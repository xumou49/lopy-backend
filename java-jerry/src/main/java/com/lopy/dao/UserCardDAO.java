package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.common.query.UserCardQuery;
import com.lopy.entity.UserCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCardDAO extends BaseMapper<UserCard> {

    List<UserCard> selectByQuery(@Param("query") UserCardQuery userCardQuery);
}
