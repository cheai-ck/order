package com.wcg.controller;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcg.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
@Controller
public class MenuController {

 @Autowired
 private RedisTemplate<Object,Object> redisTemplate;
 @RequestMapping("menu")
 public ModelAndView menu(Menu menu, HttpServletRequest request) throws IOException {

  ObjectMapper objectMapper = new ObjectMapper();
  //查询缓存
  String allMenu = (String) redisTemplate.opsForValue().get("allMenu");
  List<Menu> menuList=null;

  if (allMenu!=null) {
   //json字符串转换成泛型List
   JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Menu.class);
    menuList= objectMapper.readValue(allMenu, javaType);
  }
  if (null==menuList){
   synchronized (this){
     //缓存为空,查询数据库
     menuList = menu.selectList(null);
    System.out.println("查询的数据库！");
     //存入redis
    String str = objectMapper.writeValueAsString(menuList);
    redisTemplate.opsForValue().set("allMenu",str);
   }
  }else {
   System.out.println("查询的缓存！");
  }
  ModelAndView mv = new ModelAndView("menu");
   //List<Menu> list = menu.selectList(null);
   mv.addObject("menu",menuList);
  return mv;
 }

 @RequestMapping("main")
 public String main(){

  return "main";
 }

}
