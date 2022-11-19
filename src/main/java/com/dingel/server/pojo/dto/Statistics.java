package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Statistics)表实体类
 *
 * @author makejava
 * @since 2022-11-11 15:49:25
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("statistics")
public class Statistics  {

    @TableId(type = IdType.AUTO)
    private Integer id;


    private String statisticName;

    private float statisticCount;

    private float statisticProportion;

    private Date createtime;

    //统计类型:1：客户所在城市占比  2:航班购买占比 3： 服务被购买占比
    private Integer type;



}

