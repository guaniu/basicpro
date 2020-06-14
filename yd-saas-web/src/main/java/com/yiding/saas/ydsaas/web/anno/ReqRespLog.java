package com.yiding.saas.ydsaas.web.anno;

import java.lang.annotation.*;


/**
 * 自定义注解
 * 打印入参跟响应
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ReqRespLog {

    String value() default "打印请求响应日志";
}
