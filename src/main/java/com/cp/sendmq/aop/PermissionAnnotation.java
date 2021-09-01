package com.cp.sendmq.aop;

import java.lang.annotation.*;

/**
 * @author ASUS
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnnotation {
    String title() default "";
}
