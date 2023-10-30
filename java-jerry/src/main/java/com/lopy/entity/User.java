package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@TableName("c_user")
public class User extends BaseEntity {

    private String openId;
    private String type;
    private String email;
    private Integer platform;
    private String phone;
    private String locale;
    private Integer device;
    @TableLogic
    private Integer deleted = 0;
}
