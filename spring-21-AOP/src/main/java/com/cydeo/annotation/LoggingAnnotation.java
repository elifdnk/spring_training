package com.cydeo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // it means: I can put this annotation on top of the methods. I am defining my limits
@Retention(RetentionPolicy.RUNTIME)  //it is detail info. always we use runtime.
public @interface LoggingAnnotation {
}

//when our app compiled , after running compiled  this LoggingAnnotation is reading.