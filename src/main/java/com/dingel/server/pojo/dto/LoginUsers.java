package com.dingel.server.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户列表对象",description = "")
@TableName("users")
public class LoginUsers implements Serializable, UserDetails {
    @ApiModelProperty(value = "账号",required = true)
    @TableField("r_id")
    private String rId;

    @ApiModelProperty(value = "用户名",required = true)
    private String name;

    @ApiModelProperty(value = "密码",required = true)
    @TableField("r_password")
    private String rPassword;

    @ApiModelProperty(value = "性别:0男 1女",required = true)
    private Integer sex;

    @ApiModelProperty(value = "身份证",required = true)
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "电话",required = true)
    private String phone;

    @ApiModelProperty(value = "身份类型",required = true)
    @TableField("t_type")
    private Integer tType;

    @ApiModelProperty(value = "状态",required = true)
    private Boolean state;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return rPassword;
    }

    @Override
    public String getUsername() {
        return rId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return state;
    }


}
