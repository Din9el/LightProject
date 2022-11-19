package com.dingel.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.ProjectMapper;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
}

