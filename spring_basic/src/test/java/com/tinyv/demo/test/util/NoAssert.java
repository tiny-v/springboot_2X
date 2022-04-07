package com.tinyv.demo.test.util;

import java.lang.annotation.*;

/**
 * @author tiny_v
 * @date 2022/3/24.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAssert {
}
