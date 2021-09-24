package com.ablackpikatchu.refinement.core.util;

import com.ablackpikatchu.refinement.common.recipe.conditions.EnableableCondition;

import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

/**
 * Interface for making items enableable through a config
 * 
 * @author matyrobbrt
 *
 */
public interface IEnableable {

	boolean isEnabled();

	default IConditionSerializer<?> getCondition() {
		return EnableableCondition.Serializer.INSTANCE;
	}

}
