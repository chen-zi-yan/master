package com.hnly.provincial.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *
 * </p>
 *
 * @author maqh
 * @version 1.0
 * @since 2021-05-25
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    private InterceptorConfig interceptorConfig;

    public WebAppConfig(InterceptorConfig interceptorConfig) {
        this.interceptorConfig = interceptorConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/login");
        excludePath.add("/swagger**/**");
        excludePath.add("/v3/api-docs/**");
        excludePath.add("/docs");
        excludePath.add("/api-docs/**");
        excludePath.add("/**");

        registry.addInterceptor(interceptorConfig).addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);

    }

}
