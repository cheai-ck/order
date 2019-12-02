/*
package com.wcg.interceptor;

import com.wcg.entity.Admin;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
@Configuration
public class LoginInterceptor implements HandlerInterceptor {
 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
  //获取请求的地址
  String url = request.getRequestURI();

  //对特殊地址，直接放行
 */
/* if(url.indexOf("check")>0){
   return true;
  }*//*

  //判断session
  HttpSession session = request.getSession();
  Admin admin = (Admin)session.getAttribute("sessionAdmin");
  System.out.println(admin+"session");
  if(admin!=null){
   return true;
  }else {
   //需要身份验证
   */
/* request.getRequestDispatcher("").forward(request,response);*//*

   response.sendRedirect(request.getContextPath()+"/");  //未登录自动跳转界面
   return false;
  */
/*response.sendRedirect("http://localhost:8080/");
  return false;*//*

  }

 }

 @Override
 public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

 }

 @Override
 public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

 }
}
*/
