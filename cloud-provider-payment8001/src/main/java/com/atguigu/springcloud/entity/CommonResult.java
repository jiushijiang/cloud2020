package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果集
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    //错误编码
    private Integer code;
    //错误信息
    private String message;
    //数据
    private T  data;

    public CommonResult(Integer code,String message){

        this(code,message,null);
    }

}
