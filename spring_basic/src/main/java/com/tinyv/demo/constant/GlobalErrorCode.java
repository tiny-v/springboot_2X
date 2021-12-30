package com.tinyv.demo.constant;

import org.springframework.http.HttpStatus;

/**
 *
 * @author TinyV
 * @date 2019/12/5
 */
public enum GlobalErrorCode {

    /**************************************** 内部错误 *****************************************/

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "InternalCode", "server has internal error"),

    /**************************************** 用户异常错误码 *****************************************/

    /** 注册信息不合法 */
    REGISTER_INFO_ILLEGAL(HttpStatus.BAD_REQUEST, "RegisterError.InvalidRegisterInfo", "register info error"),
    /** 用户名已存在 */
    USER_NAME_EXIST(HttpStatus.BAD_REQUEST, "RegisterError.UserNameExist", "the user name already exist");

    /** 错误状态码 */
    HttpStatus httpStatus;
    /** 错误码 */
    String errorCode;
    /** 错误信息 */
    String errorMsg;

    GlobalErrorCode(HttpStatus httpStatus, String errorCode, String errorMsg){
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
