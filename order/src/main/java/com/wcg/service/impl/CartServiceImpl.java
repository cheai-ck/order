package com.wcg.service.impl;

import com.wcg.entity.Cart;
import com.wcg.entity.CartItem;
import com.wcg.entity.Menu;
import com.wcg.exception.CartNotFoundException;
import com.wcg.mapper.MenuMapper;
import com.wcg.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {
@Autowired
 private MenuMapper menuMapper;

 /**
  * 将菜添加到购物车
  * @param menuId
  * @param cart
  */
 @Override
 public void buyMenu(Integer menuId, Cart cart) {
        Menu menu = menuMapper.selectById(menuId);
        cart.add(menu);
 }

 @Override
 public void deleteMenu(Integer menuId, Cart cart) throws CartNotFoundException {
  if (cart == null) {
   throw new CartNotFoundException("查找不到购物车！");
  }

  Map map = cart.getMap();

  map.remove(menuId);

 }

 @Override
 public void clearcart(Cart cart) throws CartNotFoundException {
  if (cart == null) {
   throw new CartNotFoundException("查找不到购物车！");
  }
  /**
   * 清空购物车
   */
  cart.getMap().clear();
 }

 /**
  * 更新购物车数量
  * @param cart
  * @param menuId
  * @param quantity 数量
  * @throws CartNotFoundException
  */
 @Override
 public void updateCart(Cart cart, Integer menuId, int quantity) throws CartNotFoundException {
  if (cart == null) {
   throw new CartNotFoundException("查找不到购物车！");
  }
  CartItem item = cart.getMap().get(menuId);
  item.setQuantity(quantity);
 }
}
