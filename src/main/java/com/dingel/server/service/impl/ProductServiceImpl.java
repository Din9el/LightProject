package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.ProductMapper;
import com.dingel.server.pojo.dto.Excel.ExcelProducts;
import com.dingel.server.pojo.dto.Excel.ExcelUsers;
import com.dingel.server.pojo.dto.Product;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ProductService;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Workbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * (Product)表服务实现类
 *
 * @author makejava
 * @since 2022-11-23 16:13:25
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public ResponseBean insertProduct(HashMap<String, Object> map) {
        productMapper.insertProduct(map);
        return ResponseBean.success("产品添加成功");
    }



    //导出产品表格
    @Override
    public void ExportProducts(HttpServletResponse response) throws IOException {
        //获取数据
        List<ExcelProducts> products= productMapper.ExportProducts();
        //导出excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("产品列表", "产品信息", ExcelType.HSSF),   ExcelProducts.class, products);
        //设置响应头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("产品表.xls","UTF-8"));

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }



    public Integer SelectProducts(String id) {
        return productMapper.SelectProducts(id);
    }

    //若用户已存在则删除该用户再插入
    public void DeleteProducts(String id) {
        productMapper.DeleteProducts(id);
    }

    // 导入用户信息
    public void importProducts(ExcelProducts excelProducts) {
        productMapper.importProducts(excelProducts);
    }

    //用户表导入
    public ResponseBean ImportProducts(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelProducts> products= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelProducts.class,params);
        for (ExcelProducts excelProducts:products){
            if (SelectProducts(excelProducts.getId())>0){
                DeleteProducts(excelProducts.getId());

            }
            importProducts(excelProducts);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }


    //批量删除
    @Override
    public void deleteByIds(List<String> idList) {
        productMapper.deleteBatchIds(idList);
    }



}

