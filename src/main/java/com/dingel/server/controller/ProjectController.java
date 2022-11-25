package com.dingel.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "项目管理")
public class ProjectController {


    @Autowired
    private ProjectService projectService;


    @ApiOperation(value = "获取管理用户分页列表")
    @PostMapping("fetchDataPro/{page}/{limit}")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) Project projectVo) {
        Page<Project> pageParam = new Page<>(page, limit);
        QueryWrapper<Project> wrapper = new QueryWrapper<>();

        String proid = projectVo.getProid();


        if (!StringUtils.isEmpty(proid)) {
            wrapper.like("proId", proid);
        }
        Page<Project> page1 = projectService.page(pageParam, wrapper);
        return ResponseBean.success("success", page1);
    }


    @ApiOperation("项目登记")
    @PostMapping("/insertProject")
    public ResponseBean insertProject(@RequestBody HashMap<String,Object> map){
        return projectService.insertProject(map);
    }

    @ApiOperation("根据项目编号查询")
    @GetMapping("/getPro/{proid}")
    public ResponseBean getPro(@PathVariable String proid){
        Project project = projectService.getById(proid);
        return ResponseBean.success("成功",project);
    }


    @ApiOperation(value = "批量删除项目")
    @DeleteMapping("batchRemovePRo")
    public ResponseBean batchRemovePRo(@RequestBody List<String> idList){
        projectService.deleteByIds(idList);
        return ResponseBean.success("删除成功");

    }

}
