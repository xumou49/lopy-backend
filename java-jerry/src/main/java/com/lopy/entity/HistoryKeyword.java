package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("c_history_keyword")
public class HistoryKeyword {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String keyword;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}
