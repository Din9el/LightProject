package com.dingel.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.UserMapper;
import com.dingel.server.pojo.dto.*;
import com.dingel.server.pojo.dto.Excel.ExcelUsers;
import com.dingel.server.service.UserService;

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

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {
    @Resource
    private UserMapper userMapper;


    //添加用户
    @Override
    public ResponseBean insertUser(HashMap<String,Object> hashMap) {
        userMapper.insertUser(hashMap);
        return ResponseBean.success("新增成功");
    }

    //更新用户
    @Override
    public ResponseBean updateUser(HashMap<String, Object> hashMap) {
        userMapper.updateUser(hashMap);
        return ResponseBean.success("修改成功");
    }





    //导出用户表格
    @Override
    public void SelectUserToExcel(HttpServletResponse response) throws IOException {
        //获取数据
        List<ExcelUsers> users= userMapper.SelectUsersToExcel();
        //导出excel
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表", "用户信息", ExcelType.HSSF),  ExcelUsers.class, users);
        //设置响应头
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;fileName="+ URLEncoder.encode("用户表.xls","UTF-8"));

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }


    //查询导入的用户是否已存在
    public Integer SelectUser(String userId) {
        return userMapper.SelectUser(userId);
    }

    //若用户已存在则删除该用户再插入
    public void DeleteUser(String userId) {
        userMapper.DeleteUser(userId);
    }

    // 导入用户信息
    public void importUser(ExcelUsers excelUsers) {
        userMapper.importUser(excelUsers);
    }

    //用户表导入
    public ResponseBean excelInUser(MultipartFile excelFile) throws Exception {
        int t=0;
        //excel导入
        ImportParams params=new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        //获取excel上的信息
        List<ExcelUsers> users= ExcelImportUtil.importExcel(excelFile.getInputStream(),ExcelUsers.class,params);
        for (ExcelUsers excelUsers:users){
            if (SelectUser(excelUsers.getUserId())>0){
                DeleteUser(excelUsers.getUserId());

            }
            importUser(excelUsers);
            t++;
        }
        return ResponseBean.success("插入了"+t+"条数据");
    }




    //批量删除
    @Override
    public void deleteByIds(List<String> idList) {
        userMapper.deleteBathIds(idList);
    }



    //状态更新
    @Override
    public void updateStatus(Integer id, Integer state) {
        Users users = baseMapper.selectById(id);
        users.setState(state);
        baseMapper.updateById(users);
    }




}
