package com.dingel.server.controller;

import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "图表管理")
public class eChartController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping("/insertStatistics")
    @ApiOperation("插入统计记录")
    public ResponseBean insertStatistics(){
        statisticsService.insertAddressStatistics();
        statisticsService.insertLightStatistics();
        return ResponseBean.success("更新成功!");
    }

    @PostMapping("/selectStatistics")
    @ApiOperation("查询统计记录")
    public ResponseBean selStatistics(int type){
        return ResponseBean.success("",statisticsService.selectStatistics(type));
    }

}
