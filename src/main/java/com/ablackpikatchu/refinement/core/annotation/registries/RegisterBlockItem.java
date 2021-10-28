package com.ablackpikatchu.refinement.core.annotation.registries;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author matyrobbrt
 * @see RegisterItem
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface RegisterBlockItem {

}
