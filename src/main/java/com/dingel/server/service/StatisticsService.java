package com.dingel.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dingel.server.pojo.dto.Statistics;


/**
 * (Statistics)表服务接口
 *
 * @author makejava
 * @since 2022-11-11 15:49:27
 */
public interface StatisticsService extends IService<Statistics> {


    Object selectStatistics(int type);

    void insertAddressStatistics();

    void insertLightStatistics();

    void insertOrderStatistics();
}

