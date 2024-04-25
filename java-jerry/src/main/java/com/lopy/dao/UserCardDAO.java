package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.common.form.card.UserCardDTO;
import com.lopy.common.query.UserCardQuery;
import com.lopy.entity.UserCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCardDAO extends BaseMapper<UserCard> {

    @Select("select * from t_user_card where primary_flag = #{primary_flag} and user_id = #{userId}")
    List<UserCard> selectByUserIdAndPrimaryFlag(@Param("primary_flag") Integer primaryFlag, @Param("userId") Long userId);

    List<UserCard> selectByQuery(@Param("query") UserCardQuery userCardQuery);

    void save(@Param("form") UserCardDTO userCardDTO);

}
