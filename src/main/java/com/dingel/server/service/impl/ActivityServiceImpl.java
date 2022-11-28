package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.ActivityMapper;
import com.dingel.server.pojo.dto.Activity;
import com.dingel.server.pojo.dto.Excel.ExcelActivity;

import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ActivityService;
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
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2022-11-26 19:01:15
 */
@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {


    @Resource
    private ActivityMapper activityMapper;



    //导出
    @Override
    public void ExportActivity(HttpServletResponse response) throws IOException {
        //获取数据
        List<ExcelActivity> activities= activityMapper.ExportActivity();
        //导出excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("活动列表", "活动信息", ExcelType.HSSF),  ExcelActivity.class, activities);
        //设置响应头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("活动表.xls","UTF-8"));

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }

    //以下实现方法为导入所拥有的
    //查询导入是否已存在
    public Integer selectActivity(Integer id) {
        return activityMapper.selectActivity(id);
    }

    //若已存在则删除该用户再插入
    public void deleteActivity(Integer id) {
        activityMapper.deleteActivity(id);
    }

    // 导入
    public void importActivity(ExcelActivity excelActivity) {
        activityMapper.importActivity(excelActivity);
    }

    //表导入
    public ResponseBean ImportActivity(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelActivity> activities= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelActivity.class,params);
        for (ExcelActivity excelActivity:activities){
            if (selectActivity(excelActivity.getId())>0){
                deleteActivity(excelActivity.getId());
            }
            importActivity(excelActivity);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }
    //到导入的实现方法这里结束

    @Override
    public ResponseBean insertActivity(HashMap<String, Object> map) {
        activityMapper.insertActivity(map);
        return ResponseBean.success("登记成功");
    }

    @Override
    public void deleteByIds(List<String> idList) {
        activityMapper.deleteBatchIds(idList);
    }



}

