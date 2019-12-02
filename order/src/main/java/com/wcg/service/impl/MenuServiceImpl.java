package com.wcg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcg.entity.Menu;
import com.wcg.mapper.MenuMapper;
import com.wcg.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


}
