/*
package com.wcg.configuration;

import com.wcg.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfg extends WebMvcConfigurationSupport {

 @Autowired
 private LoginInterceptor interceptorConfig;

 @Override
 public void addInterceptors(InterceptorRegistry registry) {
  //注册自定义拦截器，添加拦截路径和排除拦截路径
  registry.addInterceptor(interceptorConfig).addPathPatterns("/**").excludePathPatterns("/").excludePathPatterns("/check");
 }

}*/
