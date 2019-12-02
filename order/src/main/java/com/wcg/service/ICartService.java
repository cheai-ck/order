package com.wcg.service;

import com.wcg.entity.Cart;
import com.wcg.exception.CartNotFoundException;

public interface ICartService {
 /**
  * * 将菜添加到订单
  * * @param bookid
  * * @param cart
  */
 public void buyMenu(Integer menuId, Cart cart);

 public void deleteMenu(Integer Menuid, Cart cart) throws CartNotFoundException;

 public void clearcart(Cart cart) throws CartNotFoundException;

 public void updateCart(Cart cart, Integer Menuid, int quantity) throws CartNotFoundException;
}
