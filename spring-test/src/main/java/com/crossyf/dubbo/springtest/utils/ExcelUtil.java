package com.crossyf.dubbo.springtest.utils;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelUtil {
    String value() default "";
}
