package com.dingel.server.pojo.dto;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2022-10-26 21:26:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "角色对象",description = "")
@TableName("role")
public class Role  {
    //角色id@TableId
    @ApiModelProperty(value = "角色id",required = true)
    @TableField("role_id")
    private Integer roleId;

    //角色名称
    @ApiModelProperty(value = "角色名",required = true)
    @TableField("role_name")
    private String roleName;


    //0:停用；1:启用
    @ApiModelProperty(value = "角色状态")
    @TableField("state")
    private Integer state;

    @ApiModelProperty(value = "是否被删除")
    @TableField("isdelete")
    //1:正常, 0：被删除
    private Integer isdelete;



}

