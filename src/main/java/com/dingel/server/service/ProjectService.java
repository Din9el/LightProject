package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;

import java.util.HashMap;
import java.util.List;


/**
 * (Project)表服务接口
 *
 * @author makejava
 * @since 2022-11-15 22:01:05
 */
public interface ProjectService extends IService<Project> {

    ResponseBean insertProject(HashMap<String, Object> map);

    void deleteByIds(List<String> idList);
}

