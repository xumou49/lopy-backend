package com.lopy.config.app;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "lopy.config")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LopyProps {
    private String appId;
    private String env;
    private String domain;
}
