package com.dingel.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.dingel.server.pojo.dto.Orders;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.OrdersService;
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
@Api(tags = "订单管理")
public class OrderController {


    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "获取管理用户分页列表")
    @PostMapping("fetchDataOrder/{page}/{limit}")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) Orders ordersVo) {
        Page<Orders> pageParam = new Page<>(page, limit);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();

        String ordersId = ordersVo.getOrdersId();
        String ordersName = ordersVo.getOrdersName();

        if(!StringUtils.isEmpty(ordersId)){
            wrapper.like("orders_id",ordersId);
        }


        if (!StringUtils.isEmpty(ordersName)) {
            wrapper.like("orders_name", ordersName);
        }
        Page<Orders> page1 = ordersService.page(pageParam, wrapper);
        return ResponseBean.success("success", page1);
    }

    @ApiOperation(value = "导出订单",produces = "application/octet-stream")
    @GetMapping("/ExportOrder")
    public void ExportOrder(HttpServletResponse response) throws IOException {
        ordersService.ExportOrder(response);
    }

    @ApiOperation(("导入订单"))
    @PostMapping("ImportOrder")
    public ResponseBean ImportOrder(@RequestPart(value = "file") MultipartFile excelFile) throws Exception{
        return ordersService.ImportOrder(excelFile);
    }



    @ApiOperation("项目登记")
    @PostMapping("/insertOrder")
    public ResponseBean insertOrder(@RequestBody HashMap<String,Object> map){
        return ordersService.insertOrder(map);
    }



    @ApiOperation("根据项目编号查询")
    @GetMapping("/getProOrd/{ordersId}")
    public ResponseBean getProOrd(@PathVariable String ordersId){
        Orders order = ordersService.getById(ordersId);
        return ResponseBean.success("成功",order);
    }



    @ApiOperation(value = "批量删除项目")
    @DeleteMapping("batchRemoveOrder")
    public ResponseBean batchRemoveOrder(@RequestBody List<String> idList){
        ordersService.deleteByIds(idList);
        return ResponseBean.success("删除成功");

    }


}
