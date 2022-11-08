package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Customer;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.dto.ResponsePageBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Dingel
 */
public interface CustomerService extends IService<Customer> {




    //导出
    void exportCustomerToExcel(HttpServletResponse response) throws IOException;

    //导入
    ResponseBean excelInCustomer(MultipartFile excelFile) throws Exception;

    //新增客户
    ResponseBean insertCustomer(HashMap<String, Object> hashMap);

    //更新客户
    ResponseBean updateCustomer(HashMap<String, Object> hashMap);

    //批量删除客户
    void deleteByCIds(List<String> idList);
}
