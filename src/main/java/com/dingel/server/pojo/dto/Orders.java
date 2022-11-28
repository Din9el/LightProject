package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Orders)表实体类
 *
 * @author makejava
 * @since 2022-11-28 16:20:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Orders  {
    //主键@TableId
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //订单编号
    @TableField("orders_id")
    private String ordersId;

    //订单名称
    @TableField("orders_name")
    private String ordersName;

    //订单类型
    @TableField("orders_type")
    private String ordersType;

    //订单金额
    @TableField("orders_money")
    private String ordersMoney;

    //创建时间
    @TableField("orders_createtime")
    private Date ordersCreatetime;

    //订单状态
    @TableField("orders_state")
    private Integer ordersState;



}

