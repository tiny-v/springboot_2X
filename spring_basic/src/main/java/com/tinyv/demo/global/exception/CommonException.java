package com.tinyv.demo.global.exception;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author tiny_v
 * @date 2021/8/6.
 */
@Data
public class CommonException extends RuntimeException{

    private static final Pattern PATTERN = Pattern.compile("\\$\\{\\w*\\}");

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
        this.message = build(message, params);
    }

    /**
     * 拼装message
     */
    private static String build(String source, Map<String, ?> params) {
        if (StringUtils.isEmpty(source)) {
            return "";
        }
        if (CollectionUtils.isEmpty(params)) {
            return source;
        }
        Matcher matcher = PATTERN.matcher(source);
        while (matcher.find()) {
            String match = matcher.group();
            String keyword = match.substring(2, match.length() - 1);
            if (params.containsKey(keyword)) {
                source = source.replace(match, String.valueOf(params.get(keyword)));
            }
        }
        return source;
    }



}
