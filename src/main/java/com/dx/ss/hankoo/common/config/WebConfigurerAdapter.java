package com.dx.ss.hankoo.common.config;

import com.dx.ss.hankoo.common.interceptor.DefaultInterceptor;
import com.dx.ss.hankoo.common.interceptor.exception.WebExceptionInterceptor;
import com.dx.ss.hankoo.common.interceptor.session.WebSessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //WEB端的请求拦截器
        registry.addInterceptor(new WebSessionInterceptor()).addPathPatterns("/hankoo/**/*.web", "/index");
        //默认的请求拦截器，只拦截后缀为 .do 的请求
        registry.addInterceptor(new DefaultInterceptor()).addPathPatterns("/**/*.do");
        //Web端全局异常拦截器，尽量往后注册
        registry.addInterceptor(new WebExceptionInterceptor()).addPathPatterns("/hankoo/**");
    }
}
