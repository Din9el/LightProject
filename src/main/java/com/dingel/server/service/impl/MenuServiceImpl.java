package com.dingel.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.MenuMapper;
import com.dingel.server.pojo.dto.Menu;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public ResponseBean selectAllMenu() {
        return ResponseBean.success("", menuMapper.SelectAllMenu());
    }

    @Override
    public ResponseBean selectMenuById(Integer id) {
        return ResponseBean.success("", menuMapper.SelectMenuById(id));
    }

    @Override
    public ResponseBean SelectMenuIdByRoleId(Integer id) {
        return ResponseBean.success("", menuMapper.SelectMenuIdByRoleId(id));
    }

    @Override
    public ResponseBean deleteMenusById(Integer id) {
        menuMapper.DeleteMenusById(id);
        return ResponseBean.success("");
    }

    @Override
    public ResponseBean insertMenusById(Integer rid, Integer[] mids) {
        menuMapper.InsertMenusById(rid,mids);
        return ResponseBean.success("更新成功");
    }


}
