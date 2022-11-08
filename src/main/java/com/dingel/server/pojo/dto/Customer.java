package com.dingel.server.pojo.dto;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dingel
 */
@Data
@ExcelTarget("customerExcel")
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer")
public class Customer {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @Excel(name = "客户名称")
    @TableField("customers_name")
    private String customersName;

    @Excel(name = "性别")
    @TableField("sex")
    //1：男  0：女
    private Integer sex;

    @Excel(name = "客户电话")
    @TableField("customers_phone")
    private String customersPhone;

    @Excel(name = "客户身份证")
    @TableField("customers_idCard")
    private String customersIdCard;

    @Excel(name = "客户地址")
    @TableField("customers_address")
    private String customersAddress;

    @Excel(name = "客户邮箱")
    @TableField("customers_email")
    private String customersEmail;

}
