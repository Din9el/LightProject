package com.dingel.server.pojo.dto.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductVO {

    //产品名称
    @TableField("product_Name")
    private String productName;

    //产品型号
    @TableField("product_Model")
    private String productModel;


}
