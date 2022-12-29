package com.dingel.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.Users;
import com.dingel.server.pojo.dto.vo.UserVO;
import com.dingel.server.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;



    @ApiOperation(value = "获取管理用户分页列表")
    @PostMapping("fetchData/{page}/{limit}")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) UserVO userVO) {
        Page<Users> pageParam = new Page<>(page, limit);
        QueryWrapper<Users> wrapper = new QueryWrapper<>();

        String rid = userVO.getrId();
        String name = userVO.getName();
        Integer integer = userVO.gettType();

        if(!StringUtils.isEmpty(rid)){
            wrapper.like("r_id", rid);
        }
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(integer!=null){
            wrapper.eq("t_type",integer);
        }

        Page<Users> page1 = userService.page(pageParam, wrapper);
        return ResponseBean.success("success",page1);
    }


    @ApiOperation("新增用户")
    @PostMapping("/insertUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rId",value = "账户",required = true),
            @ApiImplicitParam(name = "name",value = "姓名",required = true),
            @ApiImplicitParam(name = "phone",value = "电话",required = true),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true),
            @ApiImplicitParam(name = "id_card",value = "身份证号码",required = true),
            @ApiImplicitParam(name = "rPassword",value = "密码",required = true),
            @ApiImplicitParam(name = "tType",value = "用户类型(0:普通用户,1:管理员)",required = true)

    })
    public ResponseBean InsertUser(@RequestBody HashMap<String,Object> map){
        return userService.insertUser(map);
    }




    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true),
            @ApiImplicitParam(name = "name",value = "姓名"),
            @ApiImplicitParam(name = "tType",value = "用户类型(0:普通用户,1:管理员)"),
            @ApiImplicitParam(name = "state",value = "是否启用(1:启用,0:封禁)"),
            @ApiImplicitParam(name = "idCard",value = "身份证"),
            @ApiImplicitParam(name = "email",value = "邮箱"),
            @ApiImplicitParam(name = "phone",value = "电话号码"),
            @ApiImplicitParam(name = "sex",value = "性别(0:男,1:女)")
    })
    public ResponseBean updateUser( @RequestBody HashMap<String,Object> hashMap){
        return  userService.updateUser(hashMap);
    }



    @ApiOperation("删除用户(物理删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true)
    })
    @PostMapping(value = "/deleteUser")
    public void deleteUser( @RequestParam Integer id){
        userService.removeById(id);

    }


    @ApiOperation(value = "导出用户信息",produces = "application/octet-stream")
    @GetMapping("/ExportUsers")
    public void selectToExcel(HttpServletResponse response) throws IOException {
        userService.SelectUserToExcel(response);
    }

    @ApiOperation("导入用户信息")
    @PostMapping("/ImportUsers")
    public ResponseBean importToUser(@RequestPart(value = "file") MultipartFile excelFile) throws Exception {
        return  userService.excelInUser(excelFile);
    }


    //批量删除
    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public ResponseBean batchRemove(@RequestBody List<String> idList) {
        userService.deleteByIds(idList);
        return ResponseBean.success("删除成功");
    }





    @ApiOperation("更改状态")
    @GetMapping("updateStatus/{id}/{state}")
    public ResponseBean updateStatus(@PathVariable Integer id,@PathVariable Integer state){
        userService.updateStatus(id,state);
        return ResponseBean.success("状态更新成功");
    }


    @ApiOperation(value = "更新登录密码")
    @PostMapping("/changePassword")
    public ResponseBean ChangePassword(@RequestBody HashMap<String,Object> hashMap) {
       return userService.updatePassword(hashMap);

    }



}
