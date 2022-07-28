package com.cindy.ocrdemo.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1. 引入spring-boot-starter-aop jar包
 * 2.定义注解类
 * 3.定义日志操作的切面类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {
    // 操作描述
    String title() default "";
}
