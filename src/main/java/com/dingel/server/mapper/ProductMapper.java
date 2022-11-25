package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Excel.ExcelProducts;
import com.dingel.server.pojo.dto.Product;

import java.util.HashMap;
import java.util.List;


/**
 * (Product)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 16:13:20
 */
public interface ProductMapper extends BaseMapper<Product> {

    void insertProduct(HashMap<String, Object> map);

    List<ExcelProducts> ExportProducts();

    Integer SelectProducts(String id);

    void DeleteProducts(String id);

    void importProducts(ExcelProducts excelProducts);


}

