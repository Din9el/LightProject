package com.dingel.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dingel.server.pojo.dto.Statistics;

import java.util.List;


/**
 * (Statistics)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-11 15:49:24
 */
public interface StatisticsMapper extends BaseMapper<Statistics> {

    List<Statistics> selectAddressCount();

    int selectAllCount();

    void deleteAllStatistics(int i);

    void insertStatistics(Statistics statistics);

    List<Statistics> selectLightCount();

    int selectAllOrderCount();


    List<Statistics> selectEvaluateCount();



    List<Statistics> selectStatistics(int type);


}

