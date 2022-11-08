package com.dingel.server.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
@ApiModel(value = "菜单对象",description = "")
public class Menu {
    @ApiModelProperty(value = "菜单Id",required = true)
    @TableField("menu_id")
    @TableId
    private String menuId;
    @ApiModelProperty(value = "菜单名字",required = true)
    @TableField("menu_name")
    private String menuName;
    @ApiModelProperty(value = "URL",required = true)
    @TableField("url")
    private String url;
    @ApiModelProperty(value = "Path",required = true)
    @TableField("path")
    private String path;
    @ApiModelProperty(value = "对象名",required = true)
    @TableField("component")
    private String component;
    @ApiModelProperty(value = "父Id",required = true)
    @TableField("parentId")
    private Integer parentId;
    private List<Menu> children;
}
