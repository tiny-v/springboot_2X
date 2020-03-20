package com.tinyv.demo.global.util;

import java.util.UUID;

/**
 *
 * @author TinyV
 * @date 2019/11/22
 */
public class UUIDUtils {

    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
