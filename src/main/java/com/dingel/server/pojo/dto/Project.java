package com.dingel.server.pojo.dto;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Project)表实体类
 *
 * @author makejava
 * @since 2022-11-18 16:43:29
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("project")
public class Project  {
    @TableId
    private Integer id;

    //项目编号
    private String proid;
    //项目简称
    private String proname;
    //所属部门
    private String prodepartment;
    //立项经理
    private String promanager;
    //项目阶段 1立项 2审批 3计划 4执行 5收尾
    private Integer prostage;
    //立项时间
    private String procreatetime;
    //项目规模
    private String proscale;
    //项目地点
    private String proplace;
    //项目类型
    private String protype;
    //项目简介
    private String procontent;



}

