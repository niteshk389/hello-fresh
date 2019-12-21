package com.hellofresh.core.listeners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * Annotation to mark steps - based ont it the loggs will be created and added to HTML report
 */
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({ElementType.METHOD})
public @interface Step {
}
