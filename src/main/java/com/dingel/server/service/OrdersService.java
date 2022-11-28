package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Orders;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * (Orders)表服务接口
 *
 * @author makejava
 * @since 2022-11-28 16:20:36
 */
public interface OrdersService extends IService<Orders> {

    void ExportOrder(HttpServletResponse response)throws IOException;

    ResponseBean ImportOrder(MultipartFile excelFile)throws Exception;

    ResponseBean insertOrder(HashMap<String, Object> map);

    void deleteByIds(List<String> idList);
}

