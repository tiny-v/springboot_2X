package com.spring2x.core.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring2x.core.exception.CommonException;
import com.spring2x.core.exception.GlobalErrorCode;
import com.spring2x.core.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author tiny_v
 * @date 2021/8/6.
 */
@Component
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

    private Logger logger = LoggerFactory.getLogger(RestResponseErrorHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handleError(ClientHttpResponse response) throws CommonException {
        int rawStatusCode;
        try {
            rawStatusCode = response.getRawStatusCode();
        } catch (IOException e) {
            rawStatusCode = 500;
        }
        String body;
        if (null != getCharset(response)) {
            body = new String(getResponseBody(response), getCharset(response));
        } else {
            Charset cs = Charset.forName(StandardCharsets.UTF_8.name());
            body = new String(getResponseBody(response), cs);
        }
        try {
            BaseResponse baseResponse = objectMapper.readValue(body, BaseResponse.class);
            if(baseResponse.getData()!=null){
                throw new CommonException(rawStatusCode, baseResponse.getErrorCode(), baseResponse.getErrorMessage(), (Map) baseResponse.getData());
            }else{
                throw new CommonException(rawStatusCode, baseResponse.getErrorCode(), baseResponse.getErrorMessage());
            }
        } catch (IOException e) {
            logger.error("cannot find BaseResponse from response body", e);
            throw new CommonException(GlobalErrorCode.INTERNAL_ERROR.getHttpStatus().value(), GlobalErrorCode.INTERNAL_ERROR.getErrorCode(), body);
        }

    }

}
