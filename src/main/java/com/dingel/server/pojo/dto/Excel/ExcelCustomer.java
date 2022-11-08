package com.dingel.server.pojo.dto.Excel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ExcelTarget("customerExcel")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户列表对象",description = "")
public class ExcelCustomer {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @Excel(name = "客户名称")
    @TableField("customers_name")
    private String customersName;

    @ApiModelProperty(value = "性别:0男 1女",required = true)
    @Excel(name = "性别:0男 1女")
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
