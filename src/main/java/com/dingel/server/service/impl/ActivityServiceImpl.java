package com.dingel.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.mapper.ActivityMapper;
import com.dingel.server.pojo.dto.Activity;
import com.dingel.server.service.ActivityService;
import org.springframework.stereotype.Service;

/**
 * (Activity)表服务实现类
 *
 * @author makejava
 * @since 2022-11-26 19:01:15
 */
@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}

