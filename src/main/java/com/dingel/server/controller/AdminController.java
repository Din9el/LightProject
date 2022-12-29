package com.dingel.server.controller;

import com.dingel.server.pojo.dto.*;
import com.dingel.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


/**
 *  前端控制器
 * @author Dingel
 */




@RestController
@Api(tags = "用户登录")
public class AdminController {
    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public ResponseBean login(@RequestBody AdminLoginParam adminLoginPram, HttpServletRequest request){
        return adminService.login(adminLoginPram.getUsername(),adminLoginPram.getPassword(),adminLoginPram.getCode(),request);
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public LoginUsers getAdminInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        LoginUsers user =adminService.getAdminByRId(username);
        user.setRPassword(null);
        user.setRPassword(null);
        return user;
    }


    @ApiOperation(value = "退出登录" )
    @PostMapping("/logout")
    public ResponseBean logout(){
        return ResponseBean.success("注销成功!");
    }



}
