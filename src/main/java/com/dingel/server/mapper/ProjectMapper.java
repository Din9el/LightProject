package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Excel.ExcelProject;
import com.dingel.server.pojo.dto.Project;

import java.util.HashMap;
import java.util.List;


/**
 * (Project)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-15 22:01:03
 */
public interface ProjectMapper extends BaseMapper<Project> {

    void insertProject(HashMap<String, Object> map);

    List<ExcelProject> ExportProject();

    Integer SelectProject(String proid);

    void DeleteProject(String proid);

    void importProject(ExcelProject excelProject);
}

