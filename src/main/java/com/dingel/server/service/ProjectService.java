package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Project;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    void ExportProject(HttpServletResponse response)throws IOException;

    ResponseBean ImportProject(MultipartFile excelFile)throws Exception;
}

