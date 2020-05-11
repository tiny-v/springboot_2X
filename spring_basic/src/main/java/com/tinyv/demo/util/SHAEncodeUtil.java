package com.tinyv.demo.util;

import com.tinyv.demo.BasicApps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
public class SHAEncodeUtil {

    public static Logger logger = LoggerFactory.getLogger(BasicApps.class);

    static final String SHA = "SHA";

    public static String shaEncode(String inStr){
        if(StringUtils.isEmpty(inStr)){
            return "";
        }
        MessageDigest sha = null;
        byte[] byteArray = null;
        try {
            sha = MessageDigest.getInstance(SHA);
            byteArray = inStr.getBytes("UTF-8");
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e){
            logger.error("[SHAEncodeUtil.shaEncode]:"+e.getMessage());
            return "";
        }
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
