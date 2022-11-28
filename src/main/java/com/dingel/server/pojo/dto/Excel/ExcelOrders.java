package com.dingel.server.pojo.dto.Excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class ExcelOrders {
    //主键@TableId
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //订单编号
    @Excel(name = "订单编号")
    @TableField("orders_id")
    private String ordersId;

    //订单名称
    @Excel(name = "订单名称")
    @TableField("orders_name")
    private String ordersName;

    //订单类型
    @Excel(name = "订单类型")
    @TableField("orders_type")
    private String ordersType;

    //订单金额
    @Excel(name = "订单金额")
    @TableField("orders_money")
    private String ordersMoney;

    //创建时间
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
    @TableField("orders_createtime")
    private Date ordersCreatetime;

    //订单状态
    @Excel(name = "订单状态")
    @TableField("orders_state")
    private Integer ordersState;



}

