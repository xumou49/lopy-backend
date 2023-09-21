package com.lopy.common.dto;

import com.lopy.common.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespDTO<T> {
    private String message = CommonConstant.SUCCESS_MSG;
    private int code = 200;
    private T data;

    public RespDTO(T data) {
        this.data = data;
    }
}
