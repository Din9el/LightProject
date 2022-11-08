package com.dingel.server.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

// 通用返回结果对象

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean {
    private long code;
    private String message;
    private Object obj;


    //成功返回结果
    public static ResponseBean success(String message) {
        return new ResponseBean(200, message, null);
    }

    //成功返回结果
    public static ResponseBean success(String message, Object obj) {
        return new ResponseBean(200, message, obj);
    }

    //失败返回结果
    public static ResponseBean error(String message) {
        return new ResponseBean(500, message, null);
    }

    //失败返回结果
    public static ResponseBean error(String message, Object obj) {
        return new ResponseBean(500, message, obj);
    }
}
