package com.lopy.common.vo;

import com.lopy.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespVO<T> {
    private String message = CommonConstant.SUCCESS_MSG;
    private int code = 200;
    private T data;

    public RespVO(T data) {
        this.data = data;
    }
}
