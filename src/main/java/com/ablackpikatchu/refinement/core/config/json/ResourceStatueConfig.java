package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.core.config.entry.ResourceStatueEntry;
import com.ablackpikatchu.refinement.datafixers.util.Triple;
import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class ResourceStatueConfig extends JsonConfig {

	@Expose private List<ResourceStatueEntry> POOLS;
	
	@Override
	public String getName() {
		return "resource_statue_pools";
	}

	@Override
	protected void reset() {
		this.POOLS = new LinkedList<>();
		
		this.POOLS.add(new ResourceStatueEntry("minecraft:iron_ingot", 5, 120, 180));
		this.POOLS.add(new ResourceStatueEntry(Items.DARK_OAK_LOG, 10, 150, 300));
	}
	
	public Triple<Item, Integer, Integer> getRandomLoot(List<ResourceStatueEntry> loottable) {
        ItemStack stack = ItemStack.EMPTY;

        if (loottable == null || loottable.isEmpty())
            return new Triple<Item, Integer, Integer>(stack.getItem(), 0, 0);

        double totalWeight = 0.0;
        for (ResourceStatueEntry i : loottable) {
            totalWeight += i.weight;
        }
        
        int idx = 0;
        for (double r = Math.random() * totalWeight; idx < loottable.size() - 1; ++idx) {
            r -= loottable.get(idx).weight;
            if (r <= 0.0) break;
        }
        
        ResourceStatueEntry randomEntry = loottable.get(idx);
        
        int minAmount = 0;
        int maxAmount = 0;
        
        try {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(randomEntry.item));
            stack = new ItemStack(item);
            if (randomEntry.nbt != null) {
                CompoundNBT nbt = JsonToNBT.parseTag(randomEntry.nbt);
                stack.setTag(nbt);
            }
            minAmount = randomEntry.minAmount;
            maxAmount = randomEntry.maxAmount;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Triple<Item, Integer, Integer>(stack.getItem(), minAmount, maxAmount);
    }
	
	public Triple<Item, Integer, Integer> getRandomLoot() {
       return getRandomLoot(POOLS);
    }

}
