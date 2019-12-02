package com.wcg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
 //跳转到主页面
 @RequestMapping("/")
 public String login(){
  return "login";
 }

 //跳转到注册页面
 @RequestMapping("register")
 public String register(){
  return "register";
 }
}
