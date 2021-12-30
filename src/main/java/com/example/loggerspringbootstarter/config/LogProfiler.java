package com.example.loggerspringbootstarter.config;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogProfiler {
	String value() default "";
}
