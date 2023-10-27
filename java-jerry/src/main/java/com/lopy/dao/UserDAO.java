package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO extends BaseMapper<User> {

    @Select("select * from t_user where open_id = #{openId} and platform = #{platform} and deleted = 0")
    User getByOpenIdAndPlatform(@Param("openId") String openId, @Param("platform") Integer platform);
}
