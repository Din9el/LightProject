package com.dingel.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.PotentialCustomers;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.Users;
import com.dingel.server.pojo.dto.vo.PotentialCustomerVO;
import com.dingel.server.pojo.dto.vo.UserVO;
import com.dingel.server.service.PotentialCustomersService;
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
@Api(tags = "潜在客户管理")
public class PotentialCustomerController {


    @Autowired
    private PotentialCustomersService potentialCustomersService;

    @ApiOperation(value = "获取潜在客户分页列表")
    @PostMapping("fetchDataPC/{page}/{limit}")
    public ResponseBean fetchDataPC(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) PotentialCustomerVO potentialCustomerVO){

        Page<PotentialCustomers> pageParam = new Page<>(page, limit);
        QueryWrapper<PotentialCustomers> wrapper = new QueryWrapper<>();

        String customersName = potentialCustomerVO.getCustomersName();
        String customersIdCard = potentialCustomerVO.getCustomersIdCard();
        Integer intention = potentialCustomerVO.getIntention();

        if(!StringUtils.isEmpty(customersName)){
            wrapper.like("customers_name",customersName);
        }
        if(!StringUtils.isEmpty(customersIdCard)){
            wrapper.like("customers_idCard",customersIdCard);
        }
        if(intention != null ){
            wrapper.eq("intention",intention);
        }

        Page<PotentialCustomers> page1 = potentialCustomersService.page(pageParam,wrapper);
        return ResponseBean.success("success",page1);
    }


    @ApiOperation(value = "导出潜在客户信息" ,produces = "")
    @GetMapping("ExportPotentialCustomers")
    public void ExportPotentialCustomers(HttpServletResponse response) throws IOException{
        potentialCustomersService.ExportPotentialCustomers(response);
    }


    @ApiOperation("导入潜在客户信息")
    @PostMapping("/ImportPotentialCustomers")
    public ResponseBean importToPotentialCustomers(@RequestPart(value = "file") MultipartFile excelFile) throws Exception {
        return potentialCustomersService.importToPotentialCustomers(excelFile);
    }


    @ApiOperation("添加潜在客户信息")
    @PostMapping("/insertPotentialCustomer")
    public ResponseBean insertPotentialCustomer(@RequestBody HashMap<String,Object> hashMap){
        return potentialCustomersService.insertPotentialCustomers(hashMap);
    }

    @ApiOperation("删除客户(物理删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户id", required = true)
    })
    @PostMapping("/deletePotentialCustomer")
    public void deletePotentialCustomer(@RequestParam Integer id){
        potentialCustomersService.removeById(id);
    }



    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemovePC")
    public ResponseBean batchRemovePC(@RequestBody List<String> idList){
        potentialCustomersService.deleteByPCIds(idList);
        return ResponseBean.success("批量删除成功");
    }


    @ApiOperation("更新潜在客户")
    @PostMapping("/updatePotentialCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customersName",value = "客户名称",required = true),
            @ApiImplicitParam(name = "sex",value = "性别(0:女,1:男)",required = true),
            @ApiImplicitParam(name = "customersPhone",value = "客户电话",required = true),
            @ApiImplicitParam(name = "customersIdCard",value = "客户身份证",required = true),
            @ApiImplicitParam(name = "customersAddress",value = "客户地址",required = true),
            @ApiImplicitParam(name = "customersEmail",value = "客户邮箱",required = true),
            @ApiImplicitParam(name = "intention",value = "是否有意向购买产品",required = true),
    })
    public ResponseBean updatePotentialCustomer(@RequestBody HashMap<String,Object> hashMap){
        return potentialCustomersService.updatePotentialCustomer(hashMap);
    }














}
