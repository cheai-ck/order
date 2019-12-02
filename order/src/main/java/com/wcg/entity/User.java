package com.wcg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPasswd;

    private String userTel;

    private String userAddr;

    @TableField(exist = false)
    private String code;

    /**
     * 锁定限制登陆key： user：loginTime：lock；用户名
     * @param userName
     * @return
     */
    public static String getLoginTimeLockKey(String userName){
        return "user:loginTime:lock:"+userName;
    }
    /**
     * 登陆失败次数的key user:loginCount:fail:用户名
     */
    public static String getLoginCountFailKey(String userName){
        return "user:loginCount:fail:"+userName;
    }

}
