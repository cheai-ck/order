package com.wcg.controller;


import com.wcg.entity.Cart;
import com.wcg.entity.Menu;
import com.wcg.exception.CartNotFoundException;
import com.wcg.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-11-04
 */
@Controller
public class ShoppingController {
 @Autowired
 private ICartService cartService;
 @RequestMapping("buy")
 public String buy(@RequestParam(value = "menuId") Integer menuId, HttpServletRequest request,Menu menu){
  //从session获取订单
  Cart cart =(Cart) request.getSession().getAttribute("cart");
  if (cart==null){
   //第一次添加菜
   cart = new Cart();
   request.getSession().setAttribute("cart",cart);
  }
  cartService.buyMenu(menuId,cart);
 return "redirect:menu";
 }

 @RequestMapping("del")
 public String del(@RequestParam(value = "menuId") Integer menuId, HttpServletRequest request){
  try {
   Cart cart = (Cart) request.getSession().getAttribute("cart");
   cartService.deleteMenu(menuId, cart);
   return "redirect:getall";
  }catch (CartNotFoundException e){
   System.out.println("菜单为空！");
   return "redirect:getall";
  }
 }

 @RequestMapping("cle")
 public String cle(HttpServletRequest request){
  try {
   Cart cart = (Cart) request.getSession().getAttribute("cart");
   cartService.clearcart(cart);
   request.getSession().setAttribute("cart",null);
   return "redirect:getall";
  }catch (CartNotFoundException e){
   System.out.println("菜单为空！");
   return "redirect:getall";
  }
 }

 @RequestMapping("upda")
 public String upda(@RequestParam(value = "menuId") Integer menuId, HttpServletRequest request){
  try {
   Cart cart = (Cart) request.getSession().getAttribute("cart");
   int quantity = Integer.parseInt(request.getParameter("quantity"));
   cartService.updateCart(cart,menuId,quantity);
   return "menu";
  }catch (CartNotFoundException e){
   request.setAttribute("message","操作出错！");
   return "message";
  }
 }
}
