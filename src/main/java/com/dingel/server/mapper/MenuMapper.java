package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> SelectAllMenu();
    List<Menu> SelectMenuById(Integer id);
    List<Integer> SelectMenuIdByRoleId(Integer id);
    Integer DeleteMenusById(Integer id);
    Integer InsertMenusById(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
