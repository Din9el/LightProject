package com.dingel.server.pojo.dto.Excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class ExcelActivity {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    //活动名称
    @Excel(name = "活动名称")
    @TableField("activity_name")
    private String activityName;

    //活动地点
    @Excel(name = "活动地点")
    @TableField("activity_place")
    private String activityPlace;

    //活动预算
    @Excel(name = "活动预算")
    @TableField("activity_money")
    private String activityMoney;

    //开始时间
    @Excel(name = "开始时间",format = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;

    //结束时间
    @Excel(name = "结束时间",format = "yyyy-MM-dd")
    @TableField("end_time")
    private Date endTime;

    //活动状态
    @Excel(name = "活动状态")
    @TableField("activity_state")
    private String activityState;

    //创建人
    @Excel(name = "创建人")
    @TableField("activity_creater")
    private String activityCreater;



}

