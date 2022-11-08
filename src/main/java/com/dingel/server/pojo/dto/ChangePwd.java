package com.dingel.server.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TeacherChangePasswordParam对象", description = "")
public class ChangePwd {
    @ApiModelProperty(value = "id", required = true)
    private Integer id;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}

