package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (PotentialCustomers)表实体类
 *
 * @author makejava
 * @since 2022-11-06 21:37:58
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("potential_customers")
public class PotentialCustomers  {
    @TableId
    private Integer id;

    //潜在客户名称
    @Excel(name = "客户名称")
    @TableField("customers_name")
    private String customersName;



    //潜在客户电话
    @Excel(name = "客户电话")
    @TableField("customers_phone")
    private String customersPhone;


    //潜在客户身份证
    @Excel(name = "客户身份证")
    @TableField("customers_idCard")
    private String customersIdCard;


    //潜在客户地址
    @Excel(name = "客户地址")
    @TableField("customers_address")
    private String customersAddress;


    //潜在客户邮箱
    @Excel(name = "客户邮箱")
    @TableField("customers_email")
    private String customersEmail;



    //是否有意向购买服务(0 ：拒绝 ;  1：有意向 ; 2: 未联系)
    @ApiModelProperty(value = "状态",required = true)
    @Excel(name = "是否有意向购买服务")
    @TableField("intention")
    private Integer intention;


    @Excel(name = "性别")
    @TableField("sex")
    //1：男  0：女
    private Integer sex;



    private Date createtime;



}

