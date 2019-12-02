package com.wcg.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wcg.entity.User;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
public interface IUserService extends IService<User> {
 public String getString(String key);

 public User login(String user,String pass);

 /**
  *
  * @param uname 用户名
  * @return 给用户详细信息提示
  */
 public String loginValdate(String uname);

 /**
  * 判断当前登录用户是否被限制登陆
  * @param uname
  * @return
  */
 public Map<String,Object> loginUserLock(String uname);

}
