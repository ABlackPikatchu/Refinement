package com.ablackpikatchu.refinement.core.init;

import static com.ablackpikatchu.refinement.common.ModRarity.DARK_PURPLE_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.DARK_RED_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.GOLD_RARITY;
import static com.ablackpikatchu.refinement.common.ModRarity.*;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_ARMOUR;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_MACHINE;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_MATERIALS;
import static com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup.REFINEMENT_TOOLS_WEAPONS;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.ModRarity;
import com.ablackpikatchu.refinement.common.item.ArmorUpgrader;
import com.ablackpikatchu.refinement.common.item.AutoEjectUpgrade;
import com.ablackpikatchu.refinement.common.item.AutoImportUpgrade;
import com.ablackpikatchu.refinement.common.item.Cogwheel;
import com.ablackpikatchu.refinement.common.item.GluttonyBracelet;
import com.ablackpikatchu.refinement.common.item.GritPaper;
import com.ablackpikatchu.refinement.common.item.Magnet;
import com.ablackpikatchu.refinement.common.item.MixingBowl;
import com.ablackpikatchu.refinement.common.item.ModUpgradableArmor;
import com.ablackpikatchu.refinement.common.item.Mold;
import com.ablackpikatchu.refinement.common.item.RefinedCoal;
import com.ablackpikatchu.refinement.common.item.RefinedCoalBlockItem;
import com.ablackpikatchu.refinement.common.item.ResourceStatueItem;
import com.ablackpikatchu.refinement.common.item.UpgradeItem;
import com.ablackpikatchu.refinement.common.item.blockitem.DNASequencerBlockItem;
import com.ablackpikatchu.refinement.common.item.box.CustomLootBox;
import com.ablackpikatchu.refinement.common.item.box.ModLootBox;
import com.ablackpikatchu.refinement.common.item.box.RefinementLootBox;
import com.ablackpikatchu.refinement.common.item.food.CuringApple;
import com.ablackpikatchu.refinement.common.item.food.MinersApple;
import com.ablackpikatchu.refinement.common.item.food.MinersBread;
import com.ablackpikatchu.refinement.common.item.food.MinersCarrot;
import com.ablackpikatchu.refinement.common.item.food.MinersJerky;
import com.ablackpikatchu.refinement.common.item.food.ModEffectFood;
import com.ablackpikatchu.refinement.common.material.ModArmorMaterial;
import com.ablackpikatchu.refinement.common.material.ModItemTier;
import com.ablackpikatchu.refinement.common.te.upgrade.Upgrade;
import com.ablackpikatchu.refinement.core.annotation.registries.HoldsRegistries;
import com.ablackpikatchu.refinement.core.annotation.registries.RegisterItem;
import com.ablackpikatchu.refinement.core.config.json.LootBoxConfig.BoxType;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementArmorGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementFoodGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementItemGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementMachineGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementMaterialsGroup;
import com.ablackpikatchu.refinement.core.itemgroup.RefinementToolsWeaponsGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@HoldsRegistries
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
			() -> new Item(new Item.Properties().tab(REFINEMENT_MATERIALS)));
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
	
	// Thermal Stuff
	public static final RegistryObject<Item> LUMIUM_INGOT = normalItem("lumium_ingot", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> SIGNALUM_INGOT = normalItem("signalum_ingot", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> ENDERIUM_INGOT = normalItem("enderium_ingot", REFINEMENT_MATERIALS);
	
	public static final RegistryObject<Item> TIN_INGOT = normalItem("tin_ingot", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> SILVER_INGOT = normalItem("silver_ingot", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> LEAD_INGOT = normalItem("lead_ingot", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> COPPER_INGOT = normalItem("copper_ingot", REFINEMENT_MATERIALS);
	
	public static final RegistryObject<Item> TIN_DUST = normalItem("tin_dust", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> SILVER_DUST = normalItem("silver_dust", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> LEAD_DUST = normalItem("lead_dust", REFINEMENT_MATERIALS);
	public static final RegistryObject<Item> COPPER_DUST = normalItem("copper_dust", REFINEMENT_MATERIALS);
	
	@RegisterItem(registryName = "alpha_circuit")
	public static final Item ALPHA_CIRCUIT = tieredItem(REFINEMENT_MACHINE, ALPHA_RARITY);
	@RegisterItem(registryName = "beta_circuit")
	public static final Item BETA_CIRCUIT = tieredItem(REFINEMENT_MACHINE, BETA_RARITY);
	@RegisterItem(registryName = "gamma_circuit")
	public static final Item GAMMA_CIRCUIT = tieredItem(REFINEMENT_MACHINE, GAMMA_RARITY);
	@RegisterItem(registryName = "epsilon_circuit")
	public static final Item EPSILON_CIRCUIT = tieredItem(REFINEMENT_MACHINE, EPSILON_RARITY);
	@RegisterItem(registryName = "omega_circuit")
	public static final Item OMEGA_CIRCUIT = tieredItem(REFINEMENT_MACHINE, OMEGA_RARITY);
	
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
			() -> new Mold(new Item.Properties().tab(REFINEMENT_TOOLS_WEAPONS)));
	public static final RegistryObject<Magnet> MAGNET = ITEMS.register("magnet", Magnet::new);

	// Machine
	public static final RegistryObject<Item> MACHINE_PARTS = ITEMS.register("machine_parts",
			() -> new Item(new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
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

	public static final RegistryObject<BlockItem> VACCUMULATOR = ITEMS.register("vaccumulator",
			() -> new BlockItem(BlockInit.VACCUMULATOR.get(),
					new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE)));
	public static final RegistryObject<BlockItem> DNA_SEQUENCER = ITEMS.register("dna_sequencer",
			() -> new DNASequencerBlockItem(BlockInit.DNA_SEQUENCER.get(),
					new Item.Properties().tab(REFINEMENT_MACHINE)));

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
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_CHESTPLATE = ITEMS.register("refined_diamond_chestplate",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.CHEST,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_LEGGINGS = ITEMS.register("refined_diamond_leggings",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_DIAMOND_BOOTS = ITEMS.register("refined_diamond_boots",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDDIAMONDARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_HELMET = ITEMS.register("refined_netherite_helmet",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.HEAD,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_CHESTPLATE = ITEMS.register(
			"refined_netherite_chestplate", () -> new ModUpgradableArmor(ModArmorMaterial.REFINEDNETHERITEARMOR,
					EquipmentSlotType.CHEST, new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_LEGGINGS = ITEMS.register("refined_netherite_leggings",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.LEGS,
					new Item.Properties().tab(RefinementArmorGroup.REFINEMENT_ARMOR)));
	public static final RegistryObject<Item> REFINED_NETHERITE_BOOTS = ITEMS.register("refined_netherite_boots",
			() -> new ModUpgradableArmor(ModArmorMaterial.REFINEDNETHERITEARMOR, EquipmentSlotType.FEET,
					new Item.Properties().tab(REFINEMENT_ARMOUR)));

	// Boxes
	public static final RegistryObject<Item> FOOD_BOX = ITEMS.register("food_box",
			() -> new RefinementLootBox(
					new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).tab(RefinementItemGroup.REFINEMENT),
					BoxType.FOOD));
	
	public static final RegistryObject<Item> MOD_LOOT_BOX = ITEMS.register("mod_loot_box", ModLootBox::new);
	public static final RegistryObject<Item> CUSTOM_LOOT_BOX = ITEMS.register("custom_loot_box", CustomLootBox::new);

	// Misc Item Blocks
	public static final RegistryObject<BlockItem> BLANK_ORE_ITEM = ITEMS.register("blank_ore",
			() -> new BlockItem(BlockInit.BLANK_ORE.get(), new Item.Properties().tab(RefinementItemGroup.REFINEMENT)));
	public static final RegistryObject<BlockItem> MATERIALS_STATION_ITEM = ITEMS.register("materials_station",
			() -> new BlockItem(BlockInit.MATERIALS_STATION.get(),
					new Item.Properties().tab(RefinementItemGroup.REFINEMENT)));
	public static final RegistryObject<ResourceStatueItem> RESOURCE_STATUE_ITEM = ITEMS.register("resource_statue",
			ResourceStatueItem::new);

	// Misc Items
	public static final RegistryObject<Item> REFINED_BONEMEAL = ITEMS.register("refined_bonemeal",
			() -> new Item(new Item.Properties().tab(REFINEMENT).stacksTo(16)));
	public static final RegistryObject<Item> GLUTTONY_BRACELET = ITEMS.register("gluttony_bracelet",
			() -> new GluttonyBracelet(new Item.Properties().tab(REFINEMENT).defaultDurability(2048)));
	public static final RegistryObject<Item> ARMOR_UPGRADER = ITEMS.register("armor_upgrader",
			() -> new ArmorUpgrader(new Item.Properties().tab(REFINEMENT).stacksTo(1)));
	
	// Upgrades
	public static final RegistryObject<Item> AUTO_EJECT_UPGRADE = ITEMS.register("auto_eject_upgrade",
			() -> new AutoEjectUpgrade(new Item.Properties().tab(REFINEMENT).stacksTo(1).rarity(DARK_PURPLE_RARITY), Upgrade.AUTO_EJECT));
	public static final RegistryObject<Item> AUTO_IMPORT_UPGRADE = ITEMS.register("auto_import_upgrade",
			() -> new AutoImportUpgrade(new Item.Properties().tab(REFINEMENT).stacksTo(1).rarity(GOLD_RARITY), Upgrade.AUTO_IMPORT));
	public static final RegistryObject<Item> SPEED_UPGRADE = ITEMS.register("speed_upgrade", () -> new UpgradeItem(
			new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE).stacksTo(8).rarity(DARK_RED_RARITY), Upgrade.SPEED));
	public static final RegistryObject<Item> ENERGY_ABILITY_UPGRADE = ITEMS.register("energy_ability_upgrade", () -> new UpgradeItem(
			new Item.Properties().tab(RefinementMachineGroup.REFINEMENT_MACHINE).stacksTo(1).rarity(ModRarity.GREEN_RARITY), Upgrade.ENERGY_ABILITY));

	// Food
	public static final RegistryObject<Item> MINERS_STEW = ITEMS.register("miners_stew",
			() -> new ModEffectFood(new Item.Properties()
					.food(new Food.Builder().nutrition(6).saturationMod(13.3f).alwaysEat().fast().build())
					.rarity(Rarity.RARE).tab(RefinementFoodGroup.REFINEMENT_FOOD).stacksTo(1))
							.addEffect(new EffectInstance(Effects.DIG_SPEED, 9600, 0))
							.addEffect(new EffectInstance(Effects.NIGHT_VISION, 9600, 0))
							.hurtOnUse(DamageSourcesInit.MINERS_STEW_DAMAGE, 8.0F)
							.leftoverItem(new ItemStack(Items.BOWL)));

	public static final RegistryObject<Item> GOD_APPLE = ITEMS.register("god_apple",
			() -> new ModEffectFood(new Item.Properties().stacksTo(1).fireResistant()
					.food(new Food.Builder().nutrition(5).saturationMod(9.0f).build())
					.tab(RefinementItemGroup.REFINEMENT_FOOD).stacksTo(1))
							.addEffect(new EffectInstance(ForgeRegistries.POTIONS
									.getValue(new ResourceLocation(Refinement.MOD_ID, "ghostly_shape")), 600)));

	public static final RegistryObject<Item> MINERS_CARROT = ITEMS.register("miners_carrot", MinersCarrot::new);
	public static final RegistryObject<Item> MINERS_APPLE = ITEMS.register("miners_apple", MinersApple::new);
	public static final RegistryObject<Item> MINERS_JERKY = ITEMS.register("miners_jerky", MinersJerky::new);
	public static final RegistryObject<Item> MINERS_BREAD = ITEMS.register("miners_bread", MinersBread::new);
	public static final RegistryObject<Item> CURING_APPLE = ITEMS.register("curing_apple", CuringApple::new);
	
	private static RegistryObject<Item> normalItem(String registryName, ItemGroup tab) {
		return ITEMS.register(registryName, () -> new Item(new Item.Properties().tab(tab)));
	}
	
	private static Item tieredItem(ItemGroup tab, Rarity rarity) {
		return new Item(new Item.Properties().tab(tab).rarity(rarity));
	}

}
