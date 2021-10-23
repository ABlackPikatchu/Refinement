package com.ablackpikatchu.refinement.common.inventory;

import javax.annotation.Nonnull;

public interface IItemHandlerInventory<H> {
	
	/**
	 * <strong>Usually, should not be modified</strong>
	 * @return
	 */
	@Nonnull
	H getItemHandler();

}
