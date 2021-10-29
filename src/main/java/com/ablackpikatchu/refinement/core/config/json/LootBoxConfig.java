package com.ablackpikatchu.refinement.core.config.json;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.api.config.json.JsonConfig;
import com.ablackpikatchu.refinement.core.config.entry.CustomLootBoxEntry;
import com.ablackpikatchu.refinement.core.config.entry.ModLootBoxEntry;
import com.ablackpikatchu.refinement.core.config.entry.WeightBasedItemEntry;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.core.util.MathUtils;
import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

public class LootBoxConfig extends JsonConfig {

	@Expose
	private List<WeightBasedItemEntry> FOOD_POOLS;
	@Expose
	private List<ModLootBoxEntry> MOD_LOOT_BOXES_POOLS;
	@Expose
	private List<CustomLootBoxEntry> CUSTOM_LOOT_BOXES_POOLS;

	@Override
	public String getName() {
		return "loot_boxes_pools";
	}

	@Override
	protected void reset() {
		this.FOOD_POOLS = new LinkedList<>();

		this.FOOD_POOLS.add(new WeightBasedItemEntry(ItemInit.FoodItems.MINERS_APPLE, 2, 4, 6));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.GOLDEN_CARROT, 2, 16, 32));

		FOOD_POOLS.add(new WeightBasedItemEntry(Items.CARROT, 4, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.APPLE, 4, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKIE, 4, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.BREAD, 4, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.POTATO, 4, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.SWEET_BERRIES, 4, 20, 45));

		FOOD_POOLS.add(new WeightBasedItemEntry(Items.MELON_SLICE, 3, 20, 45));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.PUMPKIN_PIE, 3, 20, 45));

		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_BEEF, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_CHICKEN, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_COD, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_MUTTON, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_PORKCHOP, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_RABBIT, 3, 18, 40));
		FOOD_POOLS.add(new WeightBasedItemEntry(Items.COOKED_SALMON, 3, 18, 40));

		this.MOD_LOOT_BOXES_POOLS = new LinkedList<>();
		this.MOD_LOOT_BOXES_POOLS.add(new ModLootBoxEntry("mekanism",
				Lists.newArrayList(
						new WeightBasedItemEntry(mekBasic("universal_cable"), 9, 32, 64),
						new WeightBasedItemEntry(mekAdvanced("universal_cable"), 7, 32, 64),
						new WeightBasedItemEntry(mekElite("universal_cable"), 5, 32, 64),
						new WeightBasedItemEntry(mekUltimate("universal_cable"), 3, 32, 64),
						
						new WeightBasedItemEntry(mekBasic("mechanical_pipe"), 9, 32, 64),
						new WeightBasedItemEntry(mekAdvanced("mechanical_pipe"), 7, 32, 64),
						new WeightBasedItemEntry(mekElite("mechanical_pipe"), 5, 32, 64),
						new WeightBasedItemEntry(mekUltimate("mechanical_pipe"), 3, 32, 64),
						
						new WeightBasedItemEntry(mekBasic("pressurized_tube"), 9, 32, 64),
						new WeightBasedItemEntry(mekAdvanced("pressurized_tube"), 7, 32, 64),
						new WeightBasedItemEntry(mekElite("pressurized_tube"), 5, 32, 64),
						new WeightBasedItemEntry(mekUltimate("pressurized_tube"), 3, 32, 64),
						
						new WeightBasedItemEntry(mekBasic("logistical_transporter"), 9, 32, 64),
						new WeightBasedItemEntry(mekAdvanced("logistical_transporter"), 7, 32, 64),
						new WeightBasedItemEntry(mekElite("logistical_transporter"), 5, 32, 64),
						new WeightBasedItemEntry(mekUltimate("logistical_transporter"), 3, 32, 64),
						
						new WeightBasedItemEntry(mekBasic("thermodynamic_conductor"), 9, 32, 64),
						new WeightBasedItemEntry(mekAdvanced("thermodynamic_conductor"), 7, 32, 64),
						new WeightBasedItemEntry(mekElite("thermodynamic_conductor"), 5, 32, 64),
						new WeightBasedItemEntry(mekUltimate("thermodynamic_conductor"), 3, 32, 64),
						
						new WeightBasedItemEntry(mekEnriched("carbon"), 4, 16, 48),
						new WeightBasedItemEntry(mekEnriched("redstone"), 4, 16, 48),
						new WeightBasedItemEntry(mekEnriched("gold"), 4, 16, 48),
						new WeightBasedItemEntry(mekEnriched("tin"), 4, 16, 48),
						new WeightBasedItemEntry(mekEnriched("diamond"), 3, 16, 32),
						new WeightBasedItemEntry(mekEnriched("refined_obsidian"), 3, 16, 28),
						
						new WeightBasedItemEntry(mekBasic("tier_installer"), 5, 2, 5),
						new WeightBasedItemEntry(mekAdvanced("tier_installer"), 4, 2, 5),
						new WeightBasedItemEntry(mekElite("tier_installer"), 3, 1, 3),
						new WeightBasedItemEntry(mekUltimate("tier_installer"), 2, 1, 2),
						
						new WeightBasedItemEntry(mekBasic("chemical_tank"), 3, 1, 1),
						new WeightBasedItemEntry(mekAdvanced("chemical_tank"), 3, 1, 1),
						new WeightBasedItemEntry(mekElite("chemical_tank"), 2, 1, 1),
						new WeightBasedItemEntry(mekUltimate("chemical_tank"), 1, 1, 1),
						
						new WeightBasedItemEntry(mekBasic("fluid_tank"), 3, 1, 1),
						new WeightBasedItemEntry(mekAdvanced("fluid_tank"), 3, 1, 1),
						new WeightBasedItemEntry(mekElite("fluid_tank"), 2, 1, 1),
						new WeightBasedItemEntry(mekUltimate("fluid_tank"), 1, 1, 1),
						
						new WeightBasedItemEntry(mekBasic("bin"), 3),
						new WeightBasedItemEntry(mekAdvanced("bin"), 3),
						new WeightBasedItemEntry(mekElite("bin"), 2),
						new WeightBasedItemEntry(mekUltimate("bin"), 1),
						
						new WeightBasedItemEntry(mek("upgrade_speed"), 5, 2, 5),
						new WeightBasedItemEntry(mek("upgrade_energy"), 5, 2, 5),
						new WeightBasedItemEntry(mek("upgrade_gas"), 5, 2, 3),
						new WeightBasedItemEntry(mek("upgrade_anchor"), 1, 1, 1),
						
						new WeightBasedItemEntry(mek("steel_casing"), 3, 2, 5)
				)));
		
		this.MOD_LOOT_BOXES_POOLS.add(new ModLootBoxEntry("thermal", Lists.newArrayList(
						new WeightBasedItemEntry(thermal("upgrade_augument_1"), 4, 2, 5),
						new WeightBasedItemEntry(thermal("upgrade_augument_2"), 3, 2, 4),
						new WeightBasedItemEntry(thermal("upgrade_augument_3"), 1, 1, 3),
						
						
						new WeightBasedItemEntry(thermal("machine_speed_augment"), 4, 2, 6),
						new WeightBasedItemEntry(thermal("machine_efficiency_augment"), 4, 2, 6),
						new WeightBasedItemEntry(thermal("machine_output_augment"), 4, 1, 3),
						new WeightBasedItemEntry(thermal("machine_catalyst_augment"), 4, 1, 3),
						new WeightBasedItemEntry(thermal("machine_cycle_augment"), 3),
						
						new WeightBasedItemEntry(thermal("machine_frame"), 2, 3, 6),
						
						new WeightBasedItemEntry(thermal("rf_coil"), 3, 32, 48),
						new WeightBasedItemEntry(thermal("redstone_servo"), 3, 16, 40),
						
						new WeightBasedItemEntry(thermal("lumium_ingot"), 2, 16, 32),
						new WeightBasedItemEntry(thermal("signalum_ingot"), 2, 10, 32),
						new WeightBasedItemEntry(thermal("enderium_ingot"), 1, 10, 32)
				)));
		
		this.CUSTOM_LOOT_BOXES_POOLS = new LinkedList<>();
		this.CUSTOM_LOOT_BOXES_POOLS.add(new CustomLootBoxEntry("test", Lists.newArrayList(
						new WeightBasedItemEntry("test:test_item", 2, 34, 56)
				)));

	}

	public ItemStack getRandomLoot(List<WeightBasedItemEntry> loottable) {
		ItemStack stack = ItemStack.EMPTY;

		if (loottable == null || loottable.isEmpty())
			return stack;

		double totalWeight = 0.0;
		for (WeightBasedItemEntry i : loottable) {
			totalWeight += i.weight;
		}

		int idx = 0;
		for (double r = Math.random() * totalWeight; idx < loottable.size() - 1; ++idx) {
			r -= loottable.get(idx).weight;
			if (r <= 0.0)
				break;
		}

		WeightBasedItemEntry randomEntry = loottable.get(idx);

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
			int averageCount = MathUtils.getRandomNumber(minAmount, maxAmount);
			if (averageCount > stack.getMaxStackSize())
				averageCount = stack.getMaxStackSize();
			stack.setCount(averageCount);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stack;
	}
	
	public ItemStack getModLootBoxPool(String modid) {
		for (ModLootBoxEntry lootBox : this.MOD_LOOT_BOXES_POOLS) {
			if (lootBox.modid.equalsIgnoreCase(modid)) {
				return getRandomLoot(lootBox.pools);
			}
		}
		
		return ItemStack.EMPTY;
	}
	
	public ItemStack getCustomLootPoxPool(String poolName) {
		for (CustomLootBoxEntry lootBox : this.CUSTOM_LOOT_BOXES_POOLS) {
			if (lootBox.poolName.equalsIgnoreCase(poolName)) {
				return getRandomLoot(lootBox.pools);
			}
		}
		
		return ItemStack.EMPTY;
	}

	public ItemStack getLootForBox(BoxType box) {
		switch (box) {
		case FOOD:
			return getRandomLoot(FOOD_POOLS);
		default:
			break;
		}
		return ItemStack.EMPTY;
	}
	
	public String mek(String item) {
		return "mekanism:" + item;
	}
	
	public String mekBasic(String item) {
		return mek("basic_" + item);
	}
	
	public String mekAdvanced(String item) {
		return mek("advanced_" + item);
	}
	
	public String mekElite(String item) {
		return mek("elite_" + item);
	}
	
	public String mekUltimate(String item) {
		return mek("ultimate_" + item);
	}
	
	public String mekEnriched(String item) {
		return mek("enriched_" + item);
	}
	
	public String thermal(String value) {
		return "thermal:" + value;
	}

	public enum BoxType {
		FOOD;
	}
}
