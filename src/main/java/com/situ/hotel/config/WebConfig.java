package com.situ.hotel.config;

import com.situ.hotel.interceptor.CustomerInterceptor;
import com.situ.hotel.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private CustomerInterceptor customerInterceptor;

    @Value("${upload.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置图片的映射路径
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + path);
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login",
                        "/user/user/getLogin",
                        "/user/user/logout",
                        "/user/user/reg");
       /* registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/customer/**")
                .excludePathPatterns("/customer/customer/login",
                        "/customer/customer/getLogin",
                        "/customer/customer/logout",
                        "customer/customer/add",
                        "customer/customer/id");*/
    }
}
