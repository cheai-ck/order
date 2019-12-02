package com.wcg.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Cart {
 @Autowired
 private CartItem item;
 private Map<Integer,CartItem> map  = new LinkedHashMap<>();
 private int price;
 /**
  * 向购物车添加菜
  */
 public void add(Menu menu){
  //根据key获取value
  item = map.get(menu.getMenuId());

  if (item == null){
   //不存在相同菜,创建新的购物项
   item=new CartItem();
   item.setMenu(menu);
   item.setQuantity(1);
   //将点的菜项添加到购物车中
   map.put(menu.getMenuId(),item);
  }else {
   //存在相同的菜，数量加1
   item.setQuantity(item.getQuantity()+1);
  }
 }

 public int getPrice(){
  //总价格
  int totalPrice =0;
  //迭代购物车
  for (Map.Entry<Integer,CartItem> mmp :map.entrySet()){
   CartItem item = mmp.getValue();
   totalPrice += item.getPrice();
  }
  return totalPrice;
 }
}
