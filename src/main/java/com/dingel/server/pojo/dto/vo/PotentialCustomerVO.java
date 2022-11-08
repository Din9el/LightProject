package com.dingel.server.pojo.dto.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PotentialCustomerVO {

    private String customersName;

    private String customersIdCard;

    @TableField("intention")
    private Integer intention;




}
