package com.grady.mychat.config;

import com.grady.mychat.Interceptor.WxRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: mychat
 * @description: web configuration
 * @author: luchangjiang
 * @create: 2019-03-20 13:05
 **/
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getWxRequestInterceptor()).
                addPathPatterns(getIncludePathPatterns());
    }

    @Bean
    WxRequestInterceptor getWxRequestInterceptor() {
        return new WxRequestInterceptor();
    }

    /**
     * 需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/mychat/**",
                "/auth/**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
