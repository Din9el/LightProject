package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.PotentialCustomers;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * (PotentialCustomers)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 21:37:58
 */
public interface PotentialCustomersService extends IService<PotentialCustomers> {

    //导出
    void ExportPotentialCustomers(HttpServletResponse response) throws IOException;

    //导入
    ResponseBean importToPotentialCustomers(MultipartFile excelFile) throws Exception;

    //新增
    ResponseBean insertPotentialCustomers(HashMap<String, Object> hashMap);

    //批量删除
    void deleteByPCIds(List<String> idList);

    //更新
    ResponseBean updatePotentialCustomer(HashMap<String, Object> hashMap);
}

