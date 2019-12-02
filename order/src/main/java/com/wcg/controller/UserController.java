package com.wcg.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcg.entity.User;
import com.wcg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
@RestController
public class UserController {
@Autowired
private IUserService userService;

 @PostMapping("add")
 public String add(@RequestBody User user){
  boolean b= user.insert();
  return "success";
 }

 @RequestMapping("bye")
 public ModelAndView bye(HttpServletRequest request,User user){
  ModelAndView mv  =new ModelAndView("bye");
  user =(User) request.getSession().getAttribute("sessionUser");
   mv.addObject(user);
  return mv;
 }

 @ResponseBody
 @RequestMapping("check")
 public String login(@RequestBody User a, HttpServletRequest request){
  //判断当前登录用户是否被限制登陆
  Map<String,Object> map = userService.loginUserLock(a.getUserName());
  if ((boolean)map.get("flag")){
   //被限制登陆
   return "登陆失败,因"+a.getUserName()+"用户超过了限制登陆次数,已被禁止登陆，还剩"+
    map.get("lockTime")+"分钟";
  }else {
   String code = (String) request.getSession().getAttribute("verifyCode");
   //获取页面提交的验证码
   if (code.toLowerCase().equals(a.getCode().toLowerCase())) {
    a.setUserName(a.getUserName());
    a.setUserPasswd(a.getUserPasswd());
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.eq("user_name", a.getUserName()).eq("user_passwd", a.getUserPasswd());
    //判断是否登陆成功
    if (a.selectOne(wrapper) != null) {
     HttpSession session = request.getSession(true);//新建session对象
     session.setAttribute("sessionUser", a);
     return "success";
    } else {
     //不成功
     String result = userService.loginValdate(a.getUserName());
     return result;
    }
   } else {
    return "验证码错误";
   }
  }
 }
}
