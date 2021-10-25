package com.ablackpikatchu.refinement.core.anotation.registries;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ablackpikatchu.refinement.Refinement;

/**
 * Any field from a class annotated with this annotation will be scanned for any registration annotations, and if applicable will register the fields of those annotation.
 * @author matyrobbrt
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface HoldsRegstries {
	
	/**
	 * The mod id under which the registry fields will be registered
	 * @return
	 */
	String modId() default Refinement.MOD_ID;
}
