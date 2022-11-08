package com.dingel.server.controller;

import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.Role;
import com.dingel.server.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "角色管理")
public class RoleController {
    @Resource
    private RoleService roleService;
    @ApiOperation("查询角色列表")
    @GetMapping("/selectAllRole")
    public ResponseBean selectAllRole(){
        return roleService.SelectAllRole();
    }


    @ApiOperation("条件查询角色")
    @PostMapping("/TermSelectRole")
    public ResponseBean TermSelectRole(@RequestParam String roleName){
        return  roleService.TermSelectRole(roleName);
    }

    @ApiOperation("新增角色")
    @PostMapping("/InsertRole")
    public ResponseBean insertRole(@RequestParam String roleName){
        return  roleService.InsertRole(roleName);
    }

    @ApiOperation("编辑角色")
    @PostMapping("/UpdateRole")
    public ResponseBean updateRole(@RequestBody Role role){
        return  roleService.UpdateRole(role);
    }

    @ApiOperation("删除角色")
    @PostMapping("/DeleteRole")
    public ResponseBean deleteRole(@RequestParam Integer roleId){
        return  roleService.DeleteRole(roleId);
    }


}
