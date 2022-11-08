package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.CustomerMapper;
import com.dingel.server.pojo.dto.*;
import com.dingel.server.pojo.dto.Excel.ExcelCustomer;
import com.dingel.server.service.CustomerService;

import org.apache.poi.ss.usermodel.Workbook;
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
 * @author Dingel
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;



    //导出客户
    @Override
    public void exportCustomerToExcel(HttpServletResponse response) throws IOException {
        //获取数据
        List<ExcelCustomer> customers= customerMapper.selectCustomerToExcel();
        //导出excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("客户列表", "客户信息", ExcelType.HSSF),  ExcelCustomer.class, customers);
        //设置响应头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("客户表.xls","UTF-8"));

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }

    //以下实现方法为导入所拥有的
    //查询导入的客户是否已存在
    public Integer selectCustomer(Integer customerId) {
        return customerMapper.selectCustomer(customerId);
    }

    //若客户已存在则删除该用户再插入
    public void deleteCustomer(Integer customerId) {
        customerMapper.deleteCustomer(customerId);
    }

    // 导入客户信息
    public void importCustomer(ExcelCustomer excelCustomer) {
        customerMapper.importCustomer(excelCustomer);
    }

    //客户表导入
    public ResponseBean excelInCustomer(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelCustomer> customers= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelCustomer.class,params);
        for (ExcelCustomer excelCustomer:customers){
            if (selectCustomer(excelCustomer.getId())>0){
                deleteCustomer(excelCustomer.getId());
            }
            importCustomer(excelCustomer);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }
    //到导入的实现方法这里结束


    //新增客户
    @Override
    public ResponseBean insertCustomer(HashMap<String, Object> hashMap) {
         customerMapper.insertCustomer(hashMap);
         return ResponseBean.success("新增成功");
    }




    //更新客户
    @Override
    public ResponseBean updateCustomer(HashMap<String, Object> hashMap) {
        customerMapper.updateCustomer(hashMap);
        return ResponseBean.success("更新成功");
    }

    @Override
    public void deleteByCIds(List<String> idList) {
        customerMapper.deleteBathCIds(idList);;
    }


}
