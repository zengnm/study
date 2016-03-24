package com.share.example.web.annotation;

import java.lang.annotation.*;

/**
 * 是否需要验证referer标识
 * @author zengnianmei
 * @version 1.0, 2015/11/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefererCheck {
}
