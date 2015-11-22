package com.share.example.web.annotation;

import java.lang.annotation.*;

/**
 * 是否需要验证referer标识
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefererCheck {
}
