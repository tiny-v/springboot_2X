package com.tinyv.demo.global.response;


/**
 *
 * @author YMa69
 * @date 2019/12/5
 */
public class BaseResponse<T> {

    /** 状态码: 成功(1) | 失败(-1) */
    private int status;
    /** 错误码 */
    private int errorCode;

    private String msg;

    private T data;

    public BaseResponse(){}

    public BaseResponse(T data){
        this.data = data;
    }
}
