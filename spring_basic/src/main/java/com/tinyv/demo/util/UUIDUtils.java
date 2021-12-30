package com.tinyv.demo.util;

import java.util.UUID;

/**
 *
 * @author TinyV
 * @date 2019/11/22
 */
public class UUIDUtils {

    private UUIDUtils(){}

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
