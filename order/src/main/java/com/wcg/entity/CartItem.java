package com.wcg.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 购物车实体类
 */
@Data
@Component
public class CartItem {
 private Menu menu;
 //数量
 private int quantity;
 //价格
 private int price;

 /**
  * 相同菜的总价格
  * @return
  */
 public int getPrice(){
  return menu.getMenuPrice()*this.quantity;
 }
}
