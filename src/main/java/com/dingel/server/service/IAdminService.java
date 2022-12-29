package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.LoginUsers;
import com.dingel.server.pojo.dto.ResponseBean;

import javax.servlet.http.HttpServletRequest;

/**
 *  服务类
 * @author Dingel
 */

public interface IAdminService extends IService<LoginUsers> {

    /**
     * 登录之后返回token
     * @param rId
     * @param rPassword
     * @param request
     * @return
     */
    ResponseBean login(String rId, String rPassword, String code, HttpServletRequest request);
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    LoginUsers getAdminByRId(String username);
    /**
     * 根据id修改密码
     * @param rid
     * @param newPassword
     * @param request
     * @return
     */
    ResponseBean changePasswordById(String rid, String newPassword);

}
