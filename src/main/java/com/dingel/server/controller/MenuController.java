package com.dingel.server.controller;

import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@Api(tags = "菜单管理")
public class MenuController {
    @Resource
    private MenuService menuService;
    @ApiOperation("查询所有菜单")
    @GetMapping("/SelectAllMenu")
    public ResponseBean selectAllMenu(){
        return menuService.selectAllMenu();
    }

    @ApiOperation("根据用户id查询菜单")
    @PostMapping("/SelectMenuById")
    public ResponseBean selectMenuById(@RequestParam Integer id){
        return menuService.selectMenuById(id);
    }

    @ApiOperation("根据用户id查询菜单id")
    @PostMapping("/SelectMenuIdById")
    public ResponseBean selectMenuIdById(@RequestParam Integer id){
        return menuService.SelectMenuIdByRoleId(id);
    }

    @ApiOperation("更新角色菜单")
    @PostMapping("/UpdateMenus")
    public ResponseBean UpdateMenusById(Integer rid, Integer[] mids){
        System.out.println(Arrays.toString(mids));
        menuService.deleteMenusById(rid);
        menuService.insertMenusById(rid,mids);
        return ResponseBean.success("更新成功");
    }
}
