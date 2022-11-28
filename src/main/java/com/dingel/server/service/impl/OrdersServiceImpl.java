package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.OrdersMapper;
import com.dingel.server.pojo.dto.Excel.ExcelOrders;
import com.dingel.server.pojo.dto.Excel.ExcelProject;
import com.dingel.server.pojo.dto.Orders;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.OrdersService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2022-11-28 16:20:38
 */
@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public void ExportOrder(HttpServletResponse response) throws IOException {
        List<ExcelOrders> order = ordersMapper.ExportOrder();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("订单列表","订单信息", ExcelType.HSSF),ExcelOrders.class,order);
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("订单表.xls","UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }


    //查询导入的用户是否已存在
    public Integer SelectOrder(String ordersId) {
        return ordersMapper.SelectOrder(ordersId);
    }

    //若用户已存在则删除该用户再插入
    public void DeleteOrder(String ordersId) {
        ordersMapper.DeleteOrder(ordersId);
    }

    // 导入用户信息
    public void importOrder(ExcelOrders excelOrders) {
        ordersMapper.importOrder(excelOrders);
    }

    //用户表导入
    public ResponseBean ImportOrder(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelOrders> orders= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelOrders.class,params);
        for (ExcelOrders excelOrders:orders){
            if (SelectOrder(excelOrders.getOrdersId())>0){
                DeleteOrder(excelOrders.getOrdersId());

            }
            importOrder(excelOrders);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }


    @Override
    public ResponseBean insertOrder(HashMap<String, Object> map) {
        ordersMapper.insertOrder(map);
        return ResponseBean.success("登记成功");
    }

    @Override
    public void deleteByIds(List<String> idList) {
        ordersMapper.deleteBatchIds(idList);

    }

}

