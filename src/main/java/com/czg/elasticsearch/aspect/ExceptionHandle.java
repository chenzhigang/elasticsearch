package com.czg.elasticsearch.aspect;

import java.lang.annotation.*;

/**
 * 自定义异常注解类
 *
 * @author czg
 * @date 2023/10/30 15:54
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandle {

    /**
     * 操作值
     *
     * @return 默认返回空字符串
     */
    String value() default "";

    /**
     * 操作类型
     *
     * @return 默认返回null枚举类型
     */
    ExceptionOptEnum optType() default ExceptionOptEnum.NULL;

}
