package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.HistoryKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryKeywordDAO extends BaseMapper<HistoryKeyword> {

    @Select("select keyword from c_history_keyword where user_id = #{userId} order by create_date desc limit #{limit}")
    List<String> selectLatestKeywordByUserId(@Param("userId") Long userId, @Param("limit") int limit);
}
