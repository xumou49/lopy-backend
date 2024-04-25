package com.lopy.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private AuthInterceptor authInterceptor;

    @Resource
    private LogInterceptor logInterceptor;

    /**
     * @description Register custom interceptor
     * @author Dex
     * @date 2023/09/21
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                // webhook api
                .excludePathPatterns("/api/v1/webhook/**")
                // auth api
                .excludePathPatterns("/api/v1/auth/**", "/api/v1/demo/**")
                // restaurant api
                .excludePathPatterns("/api/v1/restaurant/**")
                // favicon.ico
                .excludePathPatterns("/favicon.ico")
                // swagger api
                .excludePathPatterns(getSwaggerUris());
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**")
                // favicon.ico
                .excludePathPatterns("/favicon.ico")
                // swagger api
                .excludePathPatterns(getSwaggerUris());
    }

    /**
     * @description Register path of accessing static resources
     * @author Dex
     * @date 2023/09/21
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private List<String> getSwaggerUris() {
        return Arrays.asList("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/error", "/v3/**");
    }
}
