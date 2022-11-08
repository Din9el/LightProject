package com.dingel.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.Customer;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.vo.CustomerVO;
import com.dingel.server.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @author Dingel
 */
@RestController
@Api(tags = "客户管理")
public class CustomerController {

    @Autowired
    CustomerService customerService;




    @ApiOperation(value = "获取客户分页列表")
    @PostMapping("fetchDataC/{page}/{limit}")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) CustomerVO customerVO) {
        Page<Customer> pageParam = new Page<>(page, limit);
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        String customersName = customerVO.getCustomersName();
        String customersIdCard = customerVO.getCustomersIdCard();

        if(!StringUtils.isEmpty(customersName)){
            wrapper.like("customers_name",customersName);
        }
        if(!StringUtils.isEmpty(customersIdCard)){
            wrapper.like("customers_idCard",customersIdCard);
        }

        Page<Customer> page1 = customerService.page(pageParam, wrapper);
        return ResponseBean.success("success",page1);
    }


    @ApiOperation(value = "导出客户信息",produces = "application/octet-stream")
    @GetMapping("/ExportCustomers")
    public void selectCustomerToExcel(HttpServletResponse response) throws IOException{
        customerService.exportCustomerToExcel(response);
    }

    @ApiOperation("导入客户信息")
    @PostMapping("/ImportCustomers")
    public ResponseBean importToCustomer(@RequestPart(value = "file") MultipartFile excelFile) throws Exception {
        return customerService.excelInCustomer(excelFile);
    }





    @ApiOperation("新增客户")
    @PostMapping("/insertCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customersName",value = "客户名称",required = true),
            @ApiImplicitParam(name = "sex",value = "性别(0:女,1:男)",required = true),
            @ApiImplicitParam(name = "customersPhone",value = "客户电话",required = true),
            @ApiImplicitParam(name = "customersIdCard",value = "客户身份证",required = true),
            @ApiImplicitParam(name = "customersAddress",value = "客户地址",required = true),
            @ApiImplicitParam(name = "customersEmail",value = "客户邮箱",required = true),
    })
    public ResponseBean insertCustomer(@RequestBody HashMap<String,Object> map){
        return customerService.insertCustomer(map);
    }


    @ApiOperation("更新客户")
    @PostMapping("/updateCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customersName",value = "客户名称",required = true),
            @ApiImplicitParam(name = "sex",value = "性别(0:女,1:男)",required = true),
            @ApiImplicitParam(name = "customersPhone",value = "客户电话",required = true),
            @ApiImplicitParam(name = "customersIdCard",value = "客户身份证",required = true),
            @ApiImplicitParam(name = "customersAddress",value = "客户地址",required = true),
            @ApiImplicitParam(name = "customersEmail",value = "客户邮箱",required = true),
    })
    public ResponseBean updateCustomer(@RequestBody HashMap<String,Object> hashMap){
        return customerService.updateCustomer(hashMap);
    }


    @ApiOperation("删除客户(物理删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户id", required = true)
    })
    @PostMapping("/deleteCustomer")
    public void deleteCustomer(@RequestParam Integer id){
        customerService.removeById(id);
    }


    //批量删除
    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemoveC")
    public ResponseBean batchRemoveC(@RequestBody List<String> idList){
        customerService.deleteByCIds(idList);
        return ResponseBean.success("批量删除成功");
    }



}
