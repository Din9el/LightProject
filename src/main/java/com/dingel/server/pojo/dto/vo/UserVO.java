package com.dingel.server.pojo.dto.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

public class UserVO {
    @ApiModelProperty(value = "账号",required = true)
    @TableField("r_id")
    private String rId;

    @ApiModelProperty(value = "用户名",required = true)
    private String name;

    @ApiModelProperty(value = "身份类型",required = true)
    @TableField("t_type")
    private Integer tType;


    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }
}
