package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.ProjectMapper;
import com.dingel.server.pojo.dto.Excel.ExcelProject;
import com.dingel.server.pojo.dto.Excel.ExcelUsers;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ProjectService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
 * (Project)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 22:01:06
 */
@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public ResponseBean insertProject(HashMap<String, Object> map) {
        projectMapper.insertProject(map);
        return ResponseBean.success("登记成功");
    }

    @Override
    public void deleteByIds(List<String> idList) {
        projectMapper.deleteBatchIds(idList);
    }

    @Override
    public void ExportProject(HttpServletResponse response) throws IOException {
        List<ExcelProject> projects = projectMapper.ExportProject();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("项目列表","项目信息", ExcelType.HSSF),ExcelProject.class,projects);
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("项目表.xls","UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }


    //查询导入的用户是否已存在
    public Integer SelectProject(String proid) {
        return projectMapper.SelectProject(proid);
    }

    //若用户已存在则删除该用户再插入
    public void DeleteProject(String proid) {
        projectMapper.DeleteProject(proid);
    }

    // 导入用户信息
    public void importProject(ExcelProject excelProject) {
        projectMapper.importProject(excelProject);
    }

    //用户表导入
    public ResponseBean ImportProject(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelProject> projects= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelProject.class,params);
        for (ExcelProject excelProject:projects){
            if (SelectProject(excelProject.getProid())>0){
                DeleteProject(excelProject.getProid());

            }
            importProject(excelProject);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }

}

