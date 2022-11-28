package com.dingel.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.Activity;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ActivityService;
import com.dingel.server.service.ProjectService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@Api(tags = "活动管理")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @ApiOperation(value = "获取活动分页列表")
    @PostMapping("fetchDataActivity/{page}/{limit}")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) Activity activityVo) {
        Page<Activity> pageParam = new Page<>(page, limit);
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();

        String activityName = activityVo.getActivityName();
        String activityPlace = activityVo.getActivityPlace();
        String activityCreater = activityVo.getActivityCreater();

        if (!StringUtils.isEmpty(activityCreater)){
            wrapper.like("activity_creater",activityCreater);
        }


        if (!StringUtils.isEmpty(activityPlace)) {
            wrapper.like("activity_place",activityPlace);
        }
        if(!StringUtils.isEmpty(activityName)){
            wrapper.like("activity_name",activityName);
        }
        Page<Activity> page1 = activityService.page(pageParam, wrapper);
        return ResponseBean.success("success", page1);
    }


    @ApiOperation(value = "导出活动信息",produces = "application/octet-stream")
    @GetMapping("/ExportActivity")
    public void ExportActivity(HttpServletResponse response) throws IOException {
        activityService.ExportActivity(response);
    }

    @ApiOperation("导入活动信息")
    @PostMapping("/ImportActivity")
    public ResponseBean ImportActivity(@RequestPart(value = "file") MultipartFile excelFile) throws Exception {
        return activityService.ImportActivity(excelFile);
    }

    @ApiOperation("活动登记")
    @PostMapping("/insertActivity")
    public ResponseBean insertActivity(@RequestBody HashMap<String,Object> map){
        return activityService.insertActivity(map);
    }



    @ApiOperation("根据活动编号查询")
    @GetMapping("/getProAct/{id}")
    public ResponseBean getProAct(@PathVariable String id){
        Activity activity = activityService.getById(id);
        return ResponseBean.success("成功",activity);
    }


    @ApiOperation(value = "批量删除活动")
    @DeleteMapping("batchRemoveActivity")
    public ResponseBean batchRemoveActivity(@RequestBody List<String> idList){
        activityService.deleteByIds(idList);
        return ResponseBean.success("删除成功");

    }

}
