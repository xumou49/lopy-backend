package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("c_user_card")
public class UserCard {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String stripId;
    private String brand;
    private Long userId;
    private String funding;
    private String lastFour;
    private Long expMonth;
    private Long expYear;
    private String country;
    private String cvcCheck;
    private String fingerPrint;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;

}
