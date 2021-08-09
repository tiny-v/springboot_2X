package com.tinyv.demo.global.exception;

import com.tinyv.demo.global.constant.GlobalErrorCode;

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

    public CommonException(){
        super();
    };

    public CommonException(Throwable t){
        super(t);
        this.message = t.getMessage();
        this.code = GlobalErrorCode.InternalError.getErrorCode();
        this.status = GlobalErrorCode.InternalError.getHttpStatus().value();
    }

    public CommonException(GlobalErrorCode responseCode){
        this.status = responseCode.getHttpStatus().value();
        this.code = responseCode.getErrorCode();
        this.message = responseCode.getErrorMsg();
    }

    public CommonException(GlobalErrorCode responseCode, Map<String, ?> params){
        this.status = responseCode.getHttpStatus().value();
        this.code = responseCode.getErrorCode();
        this.message =ErrorMessageConvertor.getMessage(responseCode.getErrorMsg(), params);;
    }

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
