package com.dingel.server.pojo.dto;



import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Product)表实体类
 *
 * @author makejava
 * @since 2022-11-23 16:13:22
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Product  {
    @TableId
    private Integer id;

    //产品名称
    @TableField("product_Name")
    private String productName;
    //产品类型
    @TableField("product_Type")
    private String productType;
    //产品型号
    @TableField("product_Model")
    private String productModel;
    //剩余数量
    @TableField("product_Num")
    private Integer productNum;
    //产品图片
    @TableField("product_Img")
    private String productImg;



}

