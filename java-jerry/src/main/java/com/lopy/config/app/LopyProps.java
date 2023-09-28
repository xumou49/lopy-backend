package com.lopy.config.app;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "lopy.common")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LopyProps {
    private String appId;
    private String secretKey;
    private boolean auth;
}
