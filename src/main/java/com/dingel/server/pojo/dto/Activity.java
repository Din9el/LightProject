package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Activity)表实体类
 *
 * @author makejava
 * @since 2022-11-26 19:01:12
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("activity")
public class Activity  {
    @TableId
    private Integer id;

    //活动名称
    private String activityName;
    //活动地点
    private String activityPlace;
    //活动预算
    private String activityMoney;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //活动状态
    private String activityState;
    //创建人
    private String activityCreater;



}

