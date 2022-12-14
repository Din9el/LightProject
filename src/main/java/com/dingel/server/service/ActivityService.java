package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Activity;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * (Activity)表服务接口
 *
 * @author makejava
 * @since 2022-11-26 19:01:14
 */
public interface ActivityService extends IService<Activity> {

    void ExportActivity(HttpServletResponse response)throws IOException;

    ResponseBean ImportActivity(MultipartFile excelFile)throws Exception;

    ResponseBean insertActivity(HashMap<String, Object> map);

    void deleteByIds(List<String> idList);
}

