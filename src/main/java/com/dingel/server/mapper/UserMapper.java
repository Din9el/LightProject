package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Excel.ExcelUsers;
import com.dingel.server.pojo.dto.Users;
import org.apache.ibatis.annotations.Mapper;
import java.util.HashMap;
import java.util.List;


@Mapper
public interface UserMapper extends BaseMapper<Users> {

    //批量删除
    void deleteBathIds(List<String> idList);

    //添加用户
    void insertUser(HashMap<String,Object> hashMap);

    //更新用户
    void updateUser(HashMap<String,Object> hashMap);




    //用户导出
    List<ExcelUsers> SelectUsersToExcel();


    //以下方法为导入用户具备的
    //查询导入的用户是否已存在
    Integer SelectUser(String userId);
    //删除用户，若用户已存在则删除该用户再插入
    void DeleteUser(String userId);
    //导入用户信息
    void importUser(ExcelUsers excelUsers);


}
