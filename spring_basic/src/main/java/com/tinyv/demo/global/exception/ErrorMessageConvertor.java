package com.tinyv.demo.global.exception;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tiny_v
 * @date 2021/8/6.
 */
public class ErrorMessageConvertor {

    private static Map<String, String> codeMessageMapping;

    private static final Pattern PATTERN = Pattern.compile("\\$\\{\\w*\\}");

    public static String getMessage(String source, Map<String, ?> params) {
        return build(codeMessageMapping.getOrDefault(source, source), params);
    }

    /**
     * replace all keywords by param value
     * @param source -- message that will be replaced
     * @param params -- params will be used in replacement
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
