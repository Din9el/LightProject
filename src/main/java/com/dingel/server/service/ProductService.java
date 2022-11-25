package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Product;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * (Product)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 16:13:23
 */
public interface ProductService extends IService<Product> {

    ResponseBean insertProduct(HashMap<String, Object> map);

    void ExportProducts(HttpServletResponse response)throws IOException;

    ResponseBean ImportProducts(MultipartFile excelFile) throws Exception;

    void deleteByIds(List<String> idList);
}

