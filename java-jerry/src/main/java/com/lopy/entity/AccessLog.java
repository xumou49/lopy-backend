package com.lopy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_access_log")
public class AccessLog {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String userAgent;
    private Date accessTime;
    private String remoteIp;
}
