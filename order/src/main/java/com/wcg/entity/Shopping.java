package com.wcg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Shopping implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "shopping_id", type = IdType.AUTO)
    private Integer shoppingId;

    private String shoppingUserName;

    private String shoppingMenuName;

    private Integer shoppingMenuPrice;

    private Integer shoppingAmount;


}
