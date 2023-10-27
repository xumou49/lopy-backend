package com.lopy.dao.entity;

import lombok.Data;

@Data
public class EventLog {

    private Long userId;
    private String requestUrl;
    private String requestArgs;
    private String requestMethod;
    private String requestBody;
}
