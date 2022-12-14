package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField("activity_name")
    private String activityName;

    //活动地点
    @TableField("activity_place")
    private String activityPlace;

    //活动预算
    @TableField("activity_money")
    private String activityMoney;

    //开始时间
    @TableField("start_time")
    private Date startTime;

    //结束时间
    @TableField("end_time")
    private Date endTime;

    //活动状态
    @TableField("activity_state")
    private String activityState;

    //创建人
    @TableField("activity_creater")
    private String activityCreater;



}

