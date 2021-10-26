package com.ablackpikatchu.refinement.core.annotation.registries;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Registers the item that is represented by the field that has this annotation <br>
 * In order for the registration to work, the class in which the item field is, has to be annotated with {@link HoldsRegistries}
 * 
 * @author matyrobbrt
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegisterItem {
	
	/**
	 * The registry name of the item that needs to be registered
	 * @return
	 */
	String registryName();
}
