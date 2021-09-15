package com.ablackpikatchu.refinement.core.init;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.item.Cogwheel;
import com.ablackpikatchu.refinement.common.item.GritPaper;
import com.ablackpikatchu.refinement.common.item.Magnet;
import com.ablackpikatchu.refinement.common.item.MixingBowl;
import com.ablackpikatchu.refinement.common.item.Mold;
import com.ablackpikatchu.refinement.common.item.RefinedCoal;
import com.ablackpikatchu.refinement.common.item.RefinedCoalBlockItem;
import com.ablackpikatchu.refinement.common.item.food.MinersApple;
import com.ablackpikatchu.refinement.common.item.food.MinersBread;
import com.ablackpikatchu.refinement.common.item.food.MinersCarrot;
import com.ablackpikatchu.refinement.common.item.food.MinersJerky;
import com.ablackpikatchu.refinement.common.item.food.MinersStew;
import com.ablackpikatchu.refinement.common.material.ModArmorMaterial;
import com.ablackpikatchu.refinement.common.material.ModItemTier;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementArmorGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementMachineGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementMaterialsGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementToolsWeaponsGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			Refinement.MOD_ID);

	// Materials
	// Iron
	public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_IRON_DUST = ITEMS.register("refined_iron_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> UNFIRED_REFINED_IRON_INGOT = ITEMS.register("unfired_refined_iron_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_IRON_INGOT = ITEMS.register("refined_iron_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_IRON_NUGGET = ITEMS.register("refined_iron_nugget",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<BlockItem> REFINED_IRON_BLOCK = ITEMS.register("refined_iron_block",
			() -> new BlockItem(BlockInit.REFINED_IRON_BLOCK.get(),
					new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	// Gold
	public static final RegistryObject<Item> GOLD_DUST = ITEMS.register("gold_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_GOLD_DUST = ITEMS.register("refined_gold_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> UNFIRED_REFINED_GOLD_INGOT = ITEMS.register("unfired_refined_gold_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_GOLD_INGOT = ITEMS.register("refined_gold_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_GOLD_NUGGET = ITEMS.register("refined_gold_nugget",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<BlockItem> REFINED_GOLD_BLOCK = ITEMS.register("refined_gold_block",
			() -> new BlockItem(BlockInit.REFINED_GOLD_BLOCK.get(),
					new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	// Diamond
	public static final RegistryObject<Item> DIAMOND_DUST = ITEMS.register("diamond_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_DIAMOND_DUST = ITEMS.register("refined_diamond_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> UNFIRED_REFINED_DIAMOND = ITEMS.register("unfired_refined_diamond",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_DIAMOND = ITEMS.register("refined_diamond",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<BlockItem> REFINED_DIAMOND_BLOCK = ITEMS.register("refined_diamond_block",
			() -> new BlockItem(BlockInit.REFINED_DIAMOND_BLOCK.get(),
					new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	// Netherite
	public static final RegistryObject<Item> NETHERITE_DUST = ITEMS.register("netherite_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_DUST = ITEMS.register("refined_netherite_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> UNFIRED_REFINED_NETHERITE_INGOT = ITEMS.register(
			"unfired_refined_netherite_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_INGOT = ITEMS.register("refined_netherite_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<BlockItem> REFINED_NETHERITE_BLOCK = ITEMS.register("refined_netherite_block",
			() -> new BlockItem(BlockInit.REFINED_NETHERITE_BLOCK.get(),
					new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	// Coal/Charcoal
	public static final RegistryObject<Item> COAL_DUST = ITEMS.register("coal_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> CHARCOAL_DUST = ITEMS.register("charcoal_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_CARBON_DUST = ITEMS.register("refined_carbon_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> UNFIRED_REFINED_CARBON_INGOT = ITEMS.register(
			"unfired_refined_carbon_ingot",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINED_CARBON_INGOT = ITEMS.register("refined_carbon_ingot",
			() -> new RefinedCoal(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<BlockItem> REFINED_CARBON_BLOCK = ITEMS.register("refined_carbon_block",
			() -> new RefinedCoalBlockItem());
	// Custom Materials
	public static final RegistryObject<BlockItem> PURE_CRYSTAL_ORE = ITEMS.register("pure_crystal_ore",
			() -> new BlockItem(BlockInit.PURE_CRYSTAL_ORE.get(),
					new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> PURE_CRYSTAL = ITEMS.register("pure_crystal",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> REFINING_DUST = ITEMS.register("refining_dust",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> GRIT = ITEMS.register("grit",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> IRON_INFUSED_GRIT = ITEMS.register("iron_infused_grit",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> DIAMOND_INFUSED_GRIT = ITEMS.register("diamond_infused_grit",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	public static final RegistryObject<Item> NETHERITE_INFUSED_GRIT = ITEMS.register("netherite_infused_grit",
			() -> new Item(new Item.Properties().tab(RefinementMaterialsGroup.REFINEMENT_MATERIALS)));
	// Tools & Weapons
	// Iron
	public static final RegistryObject<Item> REFINED_IRON_SWORD = ITEMS.register("refined_iron_sword",
			() -> new SwordItem(ModItemTier.REFINEDIRON, 3, -2.4F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_IRON_PICKAXE = ITEMS.register("refined_iron_pickaxe",
			() -> new PickaxeItem(ModItemTier.REFINEDIRON, 1, -2.8F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_IRON_AXE = ITEMS.register("refined_iron_axe",
			() -> new AxeItem(ModItemTier.REFINEDIRON, 6.0F, -3.1F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_IRON_SHOVEL = ITEMS.register("refined_iron_shovel",
			() -> new ShovelItem(ModItemTier.REFINEDIRON, 1.5F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_IRON_HOE = ITEMS.register("refined_iron_hoe",
			() -> new HoeItem(ModItemTier.REFINEDIRON, -2, -1.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	// Gold
	public static final RegistryObject<Item> REFINED_GOLD_SWORD = ITEMS.register("refined_gold_sword",
			() -> new SwordItem(ModItemTier.REFINEDGOLD, 3, -2.4F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_GOLD_PICKAXE = ITEMS.register("refined_gold_pickaxe",
			() -> new PickaxeItem(ModItemTier.REFINEDGOLD, 1, -2.8F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_GOLD_AXE = ITEMS.register("refined_gold_axe",
			() -> new AxeItem(ModItemTier.REFINEDGOLD, 6.0F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_GOLD_SHOVEL = ITEMS.register("refined_gold_shovel",
			() -> new ShovelItem(ModItemTier.REFINEDGOLD, 1.5F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_GOLD_HOE = ITEMS.register("refined_gold_hoe",
			() -> new HoeItem(ModItemTier.REFINEDGOLD, 0, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	// Diamond
	public static final RegistryObject<Item> REFINED_DIAMOND_SWORD = ITEMS.register("refined_diamond_sword",
			() -> new SwordItem(ModItemTier.REFINEDDIAMOND, 3, -2.4F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_DIAMOND_PICKAXE = ITEMS.register("refined_diamond_pickaxe",
			() -> new PickaxeItem(ModItemTier.REFINEDDIAMOND, 1, -2.8F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_DIAMOND_AXE = ITEMS.register("refined_diamond_axe",
			() -> new AxeItem(ModItemTier.REFINEDDIAMOND, 5.0F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_DIAMOND_SHOVEL = ITEMS.register("refined_diamond_shovel",
			() -> new ShovelItem(ModItemTier.REFINEDDIAMOND, 1.5F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_DIAMOND_HOE = ITEMS.register("refined_diamond_hoe",
			() -> new HoeItem(ModItemTier.REFINEDDIAMOND, -3, 0.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	// Netherite
	public static final RegistryObject<Item> REFINED_NETHERITE_SWORD = ITEMS.register("refined_netherite_sword",
			() -> new SwordItem(ModItemTier.REFINEDNETHERITE, 3, -2.4F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_PICKAXE = ITEMS.register("refined_netherite_pickaxe",
			() -> new PickaxeItem(ModItemTier.REFINEDNETHERITE, 1, -2.8F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_AXE = ITEMS.register("refined_netherite_axe",
			() -> new AxeItem(ModItemTier.REFINEDNETHERITE, 5.0F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_SHOVEL = ITEMS.register("refined_netherite_shovel",
			() -> new ShovelItem(ModItemTier.REFINEDNETHERITE, 1.5F, -3.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> REFINED_NETHERITE_HOE = ITEMS.register("refined_netherite_hoe",
			() -> new HoeItem(ModItemTier.REFINEDNETHERITE, -4, 0.0F,
					new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	// Custom Tools
	public static final RegistryObject<Item> MIXING_BOWL = ITEMS.register("mixing_bowl", () -> new MixingBowl(
			new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS).defaultDurability(20)));
	public static final RegistryObject<Item> GRIT_PAPER = ITEMS.register("grit_paper", () -> new GritPaper(
			new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS).defaultDurability(12)));
	public static final RegistryObject<Item> IRON_GRIT_PAPER = ITEMS.register("iron_grit_paper", () -> new GritPaper(
			new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS).defaultDurability(20)));
	public static final RegistryObject<Item> DIAMOND_GRIT_PAPER = ITEMS.register("diamond_grit_paper",
			() -> new GritPaper(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)
					.defaultDurability(26)));
	public static final RegistryObject<Item> NETHERITE_GRIT_PAPER = ITEMS.register("netherite_grit_paper",
			() -> new GritPaper(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)
					.defaultDurability(32)));
	public static final RegistryObject<Item> UNFIRED_INGOT_MOLD = ITEMS.register("unfired_ingot_mold",
			() -> new Item(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> INGOT_MOLD = ITEMS.register("ingot_mold",
			() -> new Mold(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> UNFIRED_GEM_MOLD = ITEMS.register("unfired_gem_mold",
			() -> new Item(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> GEM_MOLD = ITEMS.register("gem_mold",
			() -> new Mold(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> UNFIRED_COGWHEEL_MOLD = ITEMS.register("unfired_cogwheel_mold",
			() -> new Item(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Item> COGWHEEL_MOLD = ITEMS.register("cogwheel_mold",
			() -> new Mold(new Item.Properties().tab(RefinementToolsWeaponsGroup.REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Magnet> MAGNET = ITEMS.register("magnet", Magnet::new);

	// Machine
	public static final RegistryObject<Item> MACHINE_PARTS = ITEMS.register("machine_parts",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE).stacksTo(8)));
	public static final RegistryObject<Item> WOODEN_COGWHEEL = ITEMS.register("wooden_cogwheel",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> UNFIRED_REFINED_IRON_COGWHEEL = ITEMS.register(
			"unfired_refined_iron_cogwheel",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> REFINED_IRON_COGWHEEL = ITEMS.register("refined_iron_cogwheel",
			() -> new Cogwheel(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> UNFIRED_REFINED_GOLD_COGWHEEL = ITEMS.register(
			"unfired_refined_gold_cogwheel",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> REFINED_GOLD_COGWHEEL = ITEMS.register("refined_gold_cogwheel",
			() -> new Cogwheel(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> UNFIRED_REFINED_DIAMOND_COGWHEEL = ITEMS.register(
			"unfired_refined_diamond_cogwheel",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<Item> REFINED_DIAMOND_COGWHEEL = ITEMS.register("refined_diamond_cogwheel",
			() -> new Cogwheel(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> MACHINE_FRAME = ITEMS.register("machine_frame",
			() -> new BlockItem(BlockInit.MACHINE_FRAME.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> GRINDER = ITEMS.register("grinder",
			() -> new BlockItem(BlockInit.GRINDER.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> MIXER = ITEMS.register("mixer",
			() -> new BlockItem(BlockInit.MIXER.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> MOLD_PRESS = ITEMS.register("mold_press",
			() -> new BlockItem(BlockInit.MOLD_PRESS.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> VACCUMULATOR = ITEMS.register("vaccumulator",
			() -> new BlockItem(BlockInit.VACCUMULATOR.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));

	// Armors
	public static final RegistryObject<Item> REFINED_IRON_HELMET = ITEMS.register("refined_iron_helmet",
			() -> new ArmorItem(ModArmorMaterial.REFINEDIRONARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_IRON_CHESTPLATE = ITEMS.register("refined_iron_chestplate",
			() -> new ArmorItem(ModArmorMaterial.REFINEDIRONARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_IRON_LEGGINGS = ITEMS.register("refined_iron_leggings",
			() -> new ArmorItem(ModArmorMaterial.REFINEDIRONARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_IRON_BOOTS = ITEMS.register("refined_iron_boots",
			() -> new ArmorItem(ModArmorMaterial.REFINEDIRONARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_GOLD_HELMET = ITEMS.register("refined_gold_helmet",
			() -> new ArmorItem(ModArmorMaterial.REFINEDGOLDARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_GOLD_CHESTPLATE = ITEMS.register("refined_gold_chestplate",
			() -> new ArmorItem(ModArmorMaterial.REFINEDGOLDARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_GOLD_LEGGINGS = ITEMS.register("refined_gold_leggings",
			() -> new ArmorItem(ModArmorMaterial.REFINEDGOLDARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_GOLD_BOOTS = ITEMS.register("refined_gold_boots",
			() -> new ArmorItem(ModArmorMaterial.REFINEDGOLDARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_HELMET = ITEMS.register("refined_diamond_helmet",
			() -> new ArmorItem(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_CHESTPLATE = ITEMS.register("refined_diamond_chestplate",
			() -> new ArmorItem(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_LEGGINGS = ITEMS.register("refined_diamond_leggings",
			() -> new ArmorItem(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_BOOTS = ITEMS.register("refined_diamond_boots",
			() -> new ArmorItem(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_HELMET = ITEMS.register("refined_netherite_helmet",
			() -> new ArmorItem(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_CHESTPLATE = ITEMS
			.register("refined_netherite_chestplate", () -> new ArmorItem(ModArmorMaterial.REFINEDNETHERITEARMOR,
					EquipmentSlotType.CHEST, new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_LEGGINGS = ITEMS.register("refined_netherite_leggings",
			() -> new ArmorItem(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_BOOTS = ITEMS.register("refined_netherite_boots",
			() -> new ArmorItem(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));

	// Misc Item
	public static final RegistryObject<BlockItem> BLANK_ORE_ITEM = ITEMS.register("blank_ore",
			() -> new BlockItem(BlockInit.BLANK_ORE.get(), new Item.Properties().tab(RefinementItemGroup.REFINEMENT)));

	// Foods
	public static final RegistryObject<Item> MINERS_STEW = ITEMS.register("miners_stew", MinersStew::new);
	public static final RegistryObject<Item> MINERS_CARROT = ITEMS.register("miners_carrot", MinersCarrot::new);
	public static final RegistryObject<Item> MINERS_APPLE = ITEMS.register("miners_apple", MinersApple::new);
	public static final RegistryObject<Item> MINERS_JERKY = ITEMS.register("miners_jerky", MinersJerky::new);
	public static final RegistryObject<Item> MINERS_BREAD = ITEMS.register("miners_bread", MinersBread::new);

}