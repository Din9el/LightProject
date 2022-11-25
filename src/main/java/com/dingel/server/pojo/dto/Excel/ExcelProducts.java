package com.dingel.server.pojo.dto.Excel;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ExcelTarget("productsExcel")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "产品列表对象",description = "")
public class ExcelProducts {

    @TableField("id")
    @Excel(name = "编号")
    private String id;

    //产品名称
    @Excel(name = "产品名称")
    @TableField("product_Name")
    private String productName;


    //产品类型
    @Excel(name = "产品类型")
    @TableField("product_Type")
    private String productType;

    //产品型号
    @Excel(name = "产品型号")
    @TableField("product_Model")
    private String productModel;

    //剩余数量
    @Excel(name = "剩余数量")
    @TableField("product_Num")
    private Integer productNum;


    //产品图片
    @Excel(name = "产品图片地址")
    @TableField("product_Img")
    private String productImg;

}
