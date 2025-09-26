package com.pros.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Component()
@Target({ElementType.TYPE}) // apply only on class
public @interface QueueListener {

    String queue();

    String value();

    int prefetchCount() default 1;

    boolean isModifiable() default false;

}
