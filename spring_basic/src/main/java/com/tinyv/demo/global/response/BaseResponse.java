package com.tinyv.demo.global.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author TinyV
 * @date 2019/12/5
 */
@Getter
@Setter
@Builder
public class BaseResponse<T> implements Serializable {

    /** 状态码: 成功(1) | 失败(-1) */
    private int status;
    /** 错误码 */
    private String errorCode;

    private String errorMessage;

    private T data;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date = new Date();

    public static <T> BaseResponse.BaseResponseBuilder<T> builder() {
        return new BaseResponse.BaseResponseBuilder();
    }

}
