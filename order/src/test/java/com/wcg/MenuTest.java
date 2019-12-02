package com.wcg;

import com.wcg.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {
 @Test
 public void TestSele(){
  Menu menu = new Menu();
  List<Menu> list = menu.selectList(null);
  for (Menu menu1:list){
   System.out.println(menu1);
  }
  System.out.println(list);
 }
}
