package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Customer;
import com.dingel.server.pojo.dto.Excel.ExcelCustomer;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author Dingel
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    //导出客户
    List<ExcelCustomer> selectCustomerToExcel();


    //以下方法为导入客户具备的
    //查询导入的客户是否已存在
    Integer selectCustomer(Integer customerId);
    //删除用户，若客户已存在则删除该用户再插入
    void deleteCustomer(Integer customerId);
    //导入客户信息
    void importCustomer(ExcelCustomer excelCustomer);
    //到这结束


    //新增客户
    void insertCustomer(HashMap<String, Object> hashMap);



    //更新客户
    void updateCustomer(HashMap<String, Object> hashMap);


    //批量删除
    void deleteBathCIds(List<String> idList);
}
