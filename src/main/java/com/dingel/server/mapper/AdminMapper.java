package com.dingel.server.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.LoginUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 * @author Dingel
 */
@Mapper
public interface AdminMapper extends BaseMapper<LoginUsers> {
}
