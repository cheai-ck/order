package com.wcg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcg.entity.User;
import com.wcg.mapper.UserMapper;
import com.wcg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

 @Autowired
 private RedisTemplate redisTemplate;

 @Override
 public String getString(String key) {
  return null;
 }

 @Override
 public User login(String user, String pass) {
  return null;
 }


 /**
  * 登陆不成功的操作
  * @param uname 用户名
  * @return
  */
 @Override
 public String loginValdate(String uname) {
  //记录登陆错误次数
  String key = User.getLoginCountFailKey(uname);
  int num = 5;//错误次数
  if(!redisTemplate.hasKey(key)){
   //不存在
   //第一次失败设置次数为1失效期为2分钟
   redisTemplate.opsForValue().set(key,"1");//赋值
   redisTemplate.expire(key,2,TimeUnit.MINUTES);//设置失效期2分钟
   return "登陆失败，2分钟内允许尝试"+(num-1)+"次";
  }else{
   //存在key
   //查询失败次数
   long loginFailCount =Long.parseLong((String) redisTemplate.opsForValue().get(key));
   if (loginFailCount<num-1){
    //次数小于4
     //incr(key,1);
    redisTemplate.opsForValue().increment(key,1);//对指定key增加指定数据
    long seconds = redisTemplate.getExpire(key,TimeUnit.SECONDS);//返回秒
    return "登陆失败,在"+seconds+"秒内还允许输入"+(num-loginFailCount-1)+"次";
   }else {
    //超过登陆次数，限制登陆1小时
    redisTemplate.opsForValue().set(User.getLoginTimeLockKey(uname),"1");
    redisTemplate.expire(User.getLoginTimeLockKey(uname),1,TimeUnit.HOURS);
    return "失败次数"+num+"次,限制登陆1小时";
   }
  }
 }

 /**
  *  判断当前登录用户是否被限制登陆
  *  key存在 被限制
  */
 @Override
 public Map<String,Object> loginUserLock(String uname) {
  String key = User.getLoginTimeLockKey(uname);
  Map<String,Object> map = new HashMap<>();
  if (redisTemplate.hasKey(key)){
   long lockTime = redisTemplate.getExpire(key, TimeUnit.MINUTES);//以分钟为单位返回
   //存在
   map.put("flag",true);
   map.put("lockTime",lockTime);//还剩多长时间
  }else{
   map.put("flag",false);
  }
  return map;
 }
}
