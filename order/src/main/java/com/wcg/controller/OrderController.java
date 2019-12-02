package com.wcg.controller;


import com.wcg.entity.Cart;
import com.wcg.entity.CartItem;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-05
 */
@RestController
public class OrderController {
 //查询订单
 @RequestMapping("getall")
 public ModelAndView getall(HttpServletRequest request){
  int totalPrice=0;
  List<CartItem> list = new LinkedList<>();
  ModelAndView mv = new ModelAndView("order");
  Cart cart =(Cart) request.getSession().getAttribute("cart");
  if(cart!=null){
   for(Map.Entry<Integer, CartItem> map :cart.getMap().entrySet()){
    list.add(map.getValue());
   }
   for (Map.Entry<Integer,CartItem> mmp :cart.getMap().entrySet()){
    CartItem item = mmp.getValue();
    totalPrice += item.getPrice();
   }
  }
  mv.addObject("l",list);
  mv.addObject("price",totalPrice);
  return mv;
 }
}
