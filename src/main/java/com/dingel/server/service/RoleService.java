package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.Role;


/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2022-10-26 21:26:11
 */
public interface RoleService extends IService<Role> {

    ResponseBean SelectAllRole();

    ResponseBean TermSelectRole(String roleName);

    ResponseBean InsertRole(String roleName);

    ResponseBean UpdateRole(Role role);

    ResponseBean DeleteRole(Integer roleId);
}

