package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Excel.ExcelOrders;
import com.dingel.server.pojo.dto.Excel.ExcelProject;
import com.dingel.server.pojo.dto.Orders;

import java.util.HashMap;
import java.util.List;


/**
 * (Orders)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-28 16:20:34
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    Integer SelectOrder(String ordersId);

    void DeleteOrder(String ordersId);

    void importOrder(ExcelOrders excelOrders);

    List<ExcelOrders> ExportOrder();

    void insertOrder(HashMap<String, Object> map);
}

