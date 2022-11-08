package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Excel.ExcelPotentialCustomers;
import com.dingel.server.pojo.dto.PotentialCustomers;

import java.util.HashMap;
import java.util.List;


/**
 * (PotentialCustomers)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 21:37:57
 */
public interface PotentialCustomersMapper extends BaseMapper<PotentialCustomers> {

    //导出
    List<PotentialCustomers> ExportPotentialCustomers();


    //导入
    Integer selectPotentialCustomers(Integer customerId);
    void deletePotentialCustomers(Integer customerId);
    void importPotentialCustomers(ExcelPotentialCustomers excelPotentialCustomers);
    //导入结束


    //新增
    void insertPotentialCustomers(HashMap<String, Object> hashMap);


    //批量删除
    void deleteBathPCIds(List<String> idList);


    //更新
    void updatePotentialCustomer(HashMap<String, Object> hashMap);


}

