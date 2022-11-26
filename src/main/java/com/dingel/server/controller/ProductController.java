package com.dingel.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dingel.server.pojo.dto.Product;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.vo.ProductVO;
import com.dingel.server.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "导出产品信息",produces = "application/octet-stream")
    @GetMapping("/ExportProducts")
    public void ExportProducts(HttpServletResponse response) throws IOException {
        productService.ExportProducts(response);
    }

    @ApiOperation("导入用户信息")
    @PostMapping("/ImportProducts")
    public ResponseBean ImportProducts(@RequestPart(value = "file") MultipartFile excelFile) throws Exception {
        return  productService.ImportProducts(excelFile);
    }




    @PostMapping("fetchDataProduct/{page}/{limit}")
    public ResponseBean getPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable("page") Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @RequestBody(required = false) ProductVO productVo) {
        Page<Product> pageParam = new Page<>(page, limit);
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        String productName = productVo.getProductName();
        String productModel = productVo.getProductModel();

        if(!StringUtils.isEmpty(productName)){
            wrapper.like("product_name",productName);
        }
        if(!StringUtils.isEmpty(productModel)){
            wrapper.like("product_model",productModel);
        }

        Page<Product> page1 = productService.page(pageParam, wrapper);
        return ResponseBean.success("success",page1);
    }




    @ApiOperation("产品添加")
    @PostMapping("/insertProduct")
    public ResponseBean insertProduct(@RequestBody HashMap<String,Object> map){

        return productService.insertProduct(map);
    }



    //批量删除
    @ApiOperation(value = "根据id列表删除产产品")
    @DeleteMapping("batchRemoveProduct")
    public ResponseBean batchRemove(@RequestBody List<String> idList) {
        productService.deleteByIds(idList);
        return ResponseBean.success("删除成功");
    }

    @ApiOperation("根据项目编号查询")
    @GetMapping("/getXxx/{id}")
    public ResponseBean getXxx(@PathVariable String id){
        Product product = productService.getById(id);
        return ResponseBean.success("成功",product);
    }





}
