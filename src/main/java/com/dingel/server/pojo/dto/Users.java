package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Users)表实体类
 *
 * @author makejava
 * @since 2022-10-31 21:03:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class Users  {
    //用户id@TableId

    private Integer id;

    //注册账户名
    @TableField("r_id")
    private String rId;
    //用户名
    private String name;

    //注册密码
    @TableField("r_password")
    private String rPassword;
    //角色类型：默认为0

    @TableField("t_type")
    private Integer tType;
    //0:停用；1:启用
    private Integer state;

    //身份证
    @TableField("id_card")
    private String idCard;
    //邮箱
    private String email;
    //电话号码
    private String phone;
    //1：男  0：女
    private Integer sex;

    //1: 正常 0:被删除
    @TableField("is_delete")
    private Integer isDelete;

    private Date createtime;



}

