package com.dingel.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.RoleMapper;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.Role;
import com.dingel.server.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2022-10-26 21:26:12
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    //查询所有
    @Override
    public ResponseBean SelectAllRole() {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("isdelete",1);
        List<Role> roleList = roleMapper.selectByMap(hashMap);
        return ResponseBean.success("查询成功", roleList);
    }

    //条件查询
    @Override
    public ResponseBean TermSelectRole(String roleName) {
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("isdelete",1);
        if (!"".equals(roleName) && null != roleName){
            hashMap.put("role_name",roleName);
        }
        return ResponseBean.success("查询成功", roleMapper.selectByMap(hashMap));
    }


    //添加
    @Override
    public ResponseBean InsertRole(String roleName) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("isdelete",1);
        hashMap.put("role_name",roleName);
        Role role = new Role();
        role.setRoleName(roleName);
        if (roleMapper.selectByMap(hashMap).size()==0){
            roleMapper.insert(role);
            return ResponseBean.success("新增角色成功");
        }
        else {
            return ResponseBean.error("该角色类型已存在");
        }
    }



    //更新
    @Override
    public ResponseBean UpdateRole(Role role) {
        UpdateWrapper<Role> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("role_id",role.getRoleId()).set("role_name",role.getRoleName());
        roleMapper.update(null,updateWrapper);
        return ResponseBean.success("修改角色成功");
    }




    //删除
    @Override
    public ResponseBean DeleteRole(Integer roleId) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("role_id",roleId).set("isdelete",0);
        roleMapper.update(null,updateWrapper);
        return ResponseBean.success("删除角色成功");
    }




}

