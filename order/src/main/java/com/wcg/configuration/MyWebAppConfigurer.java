package com.wcg.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 资源映射路径
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

/* *//**上传地址*//*
 @Value("${file.upload.path}")
 private String filePath;
 *//**显示相对地址*//*
 @Value("${file.upload.path.relative}")
 private String fileRelativePath;*/

 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
 /* registry.addResourceHandler(fileRelativePath).
   addResourceLocations("file:/" + filePath);
*/
  //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
  registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
  registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
  //其他静态资源
  registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

 }



}

