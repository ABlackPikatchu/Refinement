package com.ablackpikatchu.refinement.core.annotation.registries;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface OnRegistryEvent {
	
	RegistryEvent eventType();
	
	public enum RegistryEvent {
		ITEMS;
	}

}
