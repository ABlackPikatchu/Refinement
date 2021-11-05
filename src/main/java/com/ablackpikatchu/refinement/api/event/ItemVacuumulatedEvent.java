package com.ablackpikatchu.refinement.api.event;

import com.ablackpikatchu.refinement.common.te.misc_tes.VacuumulatorTileEntity;

import net.minecraft.entity.item.ItemEntity;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Called before a {@link VacuumulatorTileEntity} vacuumulates an item! If the
 * event is cancelled the item is not vacuumulated
 * 
 * @author matyrobbrt
 *
 */
@Cancelable
public class ItemVacuumulatedEvent extends RefinementEvent {

	private final VacuumulatorTileEntity vacuumulator;
	private final ItemEntity item;

	private ItemVacuumulatedEvent(VacuumulatorTileEntity vacuumulator, ItemEntity item) {
		this.vacuumulator = vacuumulator;
		this.item = item;
	}

	public VacuumulatorTileEntity getVacuumulator() { return this.vacuumulator; }

	public ItemEntity getItem() { return this.item; }
	
	@Override
	public boolean isCancelable() { return true; }
	
	public static ItemVacuumulatedEvent onItemVacuumulated(VacuumulatorTileEntity vacuumulator, ItemEntity item) {
		ItemVacuumulatedEvent event = new ItemVacuumulatedEvent(vacuumulator, item);
		MinecraftForge.EVENT_BUS.post(event);
		return event;
	}

}
