package com.atguigu.springcloud.alibaba.config.xid;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangyaochong
 * @date 2020/3/19 23:04
 */
@Component
public class SeataHandlerInterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//注册HandlerInterceptor，拦截所有请求
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
    }
}
