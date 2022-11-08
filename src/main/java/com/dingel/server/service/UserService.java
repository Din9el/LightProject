package com.dingel.server.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.ResponseBean;

import com.dingel.server.pojo.dto.ResponsePageBean;

import com.dingel.server.pojo.dto.Users;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface UserService extends IService<Users> {



    //添加用户
    ResponseBean insertUser(HashMap<String,Object> hashMap);

    //更新用户
    ResponseBean updateUser(HashMap<String,Object> hashMap);



    //用户导出
    void SelectUserToExcel(HttpServletResponse response) throws IOException;

    //用户导入
    ResponseBean excelInUser(MultipartFile excelFile) throws Exception;

    //批量删除
    void deleteByIds(List<String> idList);

    //更改状态
    void updateStatus(Integer id, Integer state);


}
