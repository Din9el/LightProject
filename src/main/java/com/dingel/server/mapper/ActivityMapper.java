package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Activity;
import com.dingel.server.pojo.dto.Excel.ExcelActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * (Activity)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-26 19:01:11
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<ExcelActivity> ExportActivity();

    Integer selectActivity(Integer id);

    void deleteActivity(Integer id);

    void importActivity(ExcelActivity excelActivity);

    void insertActivity(HashMap<String, Object> map);
}

