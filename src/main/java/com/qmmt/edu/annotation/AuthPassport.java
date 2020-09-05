/**
 *Copyright: Copyright (c) 2015
 */
package com.qmmt.edu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wenghongbo
 *
 * 2015年6月12日下午1:47:14
 */



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthPassport {
	//boolean validate() default true;
}
