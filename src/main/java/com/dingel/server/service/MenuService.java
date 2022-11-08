package com.dingel.server.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Menu;
import com.dingel.server.pojo.dto.ResponseBean;


public interface MenuService extends IService<Menu> {
    ResponseBean selectAllMenu();
    ResponseBean selectMenuById(Integer id);
    ResponseBean SelectMenuIdByRoleId(Integer id);
    ResponseBean deleteMenusById(Integer id);
    ResponseBean insertMenusById(Integer rid, Integer[] mids);
}
