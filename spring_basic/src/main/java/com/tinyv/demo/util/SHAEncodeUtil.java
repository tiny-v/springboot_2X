package com.tinyv.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
public class SHAEncodeUtil {

    private SHAEncodeUtil(){}

    public static final Logger logger = LoggerFactory.getLogger(SHAEncodeUtil.class);

    static final String SHA = "SHA";

    public static String shaEncode(String inStr){
        if(StringUtils.isEmpty(inStr)){
            return "";
        }
        MessageDigest sha = null;
        byte[] byteArray = null;
        try {
            sha = MessageDigest.getInstance(SHA);
            byteArray = inStr.getBytes(StandardCharsets.UTF_8.name());
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e){
            logger.error("[SHAEncodeUtil.shaEncode] error.", e);
            return "";
        }
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = (md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
