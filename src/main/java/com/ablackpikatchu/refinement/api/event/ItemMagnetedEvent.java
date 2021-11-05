package com.ablackpikatchu.refinement.api.event;

import com.ablackpikatchu.refinement.common.item.Magnet;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * Called before a {@link Magnet} magnets an item! If the
 * event is cancelled the item is not magneted
 * 
 * @author matyrobbrt
 *
 */
@Cancelable
public class ItemMagnetedEvent extends RefinementEvent {

	private final PlayerEntity player;
	private final ItemEntity item;

	private ItemMagnetedEvent(PlayerEntity player, ItemEntity item) {
		this.player = player;
		this.item = item;
	}

	public PlayerEntity getPlayer() { return this.player; }

	public ItemEntity getItem() { return this.item; }
	
	@Override
	public boolean isCancelable() { return true; }
	
	public static ItemMagnetedEvent onItemVacuumulated(PlayerEntity player, ItemEntity item) {
		ItemMagnetedEvent event = new ItemMagnetedEvent(player, item);
		MinecraftForge.EVENT_BUS.post(event);
		return event;
	}
	
}
