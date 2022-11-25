package com.dingel.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.StatisticsMapper;
import com.dingel.server.pojo.dto.Statistics;
import com.dingel.server.service.StatisticsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Statistics)表服务实现类
 *
 * @author makejava
 * @since 2022-11-11 15:49:28
 */
@Service("statisticsService")
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    @Scheduled(cron = "0 0/10 * * * ?")
    public void insertAddressStatistics() {
        List<Statistics> statisticsList = statisticsMapper.selectAddressCount();
        int allCount = statisticsMapper.selectAllCount();
        statisticsMapper.deleteAllStatistics(1);
        for (Statistics statistics : statisticsList){
            statistics.setStatisticProportion(statistics.getStatisticCount()/allCount);
            statistics.setType(1);
            statisticsMapper.insertStatistics(statistics);
        }

    }

    @Override
    @Scheduled(cron = "0 0 9 * * ?")
    public void insertLightStatistics() {
        List<Statistics> statisticsBeans = statisticsMapper.selectLightCount();
        int allCount = statisticsMapper.selectAllOrderCount();
        statisticsMapper.deleteAllStatistics(2);
        for (Statistics statistics : statisticsBeans){
            statistics.setStatisticProportion(statistics.getStatisticCount()/allCount);
            statistics.setType(2);
            statisticsMapper.insertStatistics(statistics);
        }
    }


    @Override
    public List<Statistics> selectStatistics(int type) {
        return statisticsMapper.selectStatistics(type);
    }
}

