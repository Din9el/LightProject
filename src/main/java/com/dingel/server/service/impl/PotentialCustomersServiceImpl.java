package com.dingel.server.service.impl;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.PotentialCustomersMapper;
import com.dingel.server.pojo.dto.Excel.ExcelCustomer;
import com.dingel.server.pojo.dto.Excel.ExcelPotentialCustomers;
import com.dingel.server.pojo.dto.PotentialCustomers;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.PotentialCustomersService;
import org.apache.poi.ss.usermodel.Workbook;
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
 * (PotentialCustomers)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 21:38:01
 */
@Service("potentialCustomersService")
public class PotentialCustomersServiceImpl extends ServiceImpl<PotentialCustomersMapper, PotentialCustomers> implements PotentialCustomersService {

    @Resource
    private PotentialCustomersMapper potentialCustomersMapper;
    //导出
    @Override
    public void ExportPotentialCustomers(HttpServletResponse response) throws IOException {
        List<PotentialCustomers> potentialCustomers = potentialCustomersMapper.ExportPotentialCustomers();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("潜在客户列表","潜在客户信息", ExcelType.HSSF), ExcelPotentialCustomers.class,potentialCustomers );
        //设置响应头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("潜在客户表.xls","UTF-8"));

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();



    }




    //以下实现方法为导入所拥有的
    //查询导入的客户是否已存在
    public Integer selectPotentialCustomers(Integer customerId) {
        return potentialCustomersMapper.selectPotentialCustomers(customerId);
    }
    //若客户已存在则删除该用户再插入
    public void deletePotentialCustomers(Integer customerId) {
        potentialCustomersMapper.deletePotentialCustomers(customerId);
    }
    // 导入客户信息
    public void importPotentialCustomers(ExcelPotentialCustomers excelPotentialCustomers) {
        potentialCustomersMapper.importPotentialCustomers(excelPotentialCustomers);
    }
    //客户表导入
    public ResponseBean importToPotentialCustomers(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelPotentialCustomers> potentialCustomers= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelPotentialCustomers.class,params);
        for (ExcelPotentialCustomers excelPotentialCustomers:potentialCustomers){
            if (selectPotentialCustomers(excelPotentialCustomers.getId())>0){
                deletePotentialCustomers(excelPotentialCustomers.getId());
            }
            importPotentialCustomers(excelPotentialCustomers);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }
    //到导入的实现方法这里结束


    //新增潜在客户
    @Override
    public ResponseBean insertPotentialCustomers(HashMap<String, Object> hashMap) {
        potentialCustomersMapper.insertPotentialCustomers(hashMap);
        return ResponseBean.success("添加成功");
    }

    @Override
    public void deleteByPCIds(List<String> idList) {
        potentialCustomersMapper.deleteBathPCIds(idList);;
    }

    @Override
    public ResponseBean updatePotentialCustomer(HashMap<String, Object> hashMap) {
        potentialCustomersMapper.updatePotentialCustomer(hashMap);
        return ResponseBean.success("更新成功");
    }

}

