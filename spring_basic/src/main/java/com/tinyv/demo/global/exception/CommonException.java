package com.tinyv.demo.global.exception;

import java.util.Map;


/**
 * @author tiny_v
 * @date 2021/8/6.
 */
public class CommonException extends RuntimeException{

    /** 错误码 */
    private String code;
    /** 错误信息 */
    private String message;
    /** 状态码 */
    private int status;
    /** 参数 */
    private Map<String, ?> params;

    public CommonException(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public CommonException(int status, String code, String message, Map<String, ?> params){
        this.status = status;
        this.code = code;
        this.message = ErrorMessageConvertor.getMessage(message, params);
    }



}
