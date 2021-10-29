package com.ablackpikatchu.refinement.data.patchouli;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.api.datagen.patchouli.PatchouliGenProvider;
import com.ablackpikatchu.refinement.api.datagen.patchouli.page.CraftingRecipePage;
import com.ablackpikatchu.refinement.api.datagen.patchouli.page.SpotlightPage;
import com.ablackpikatchu.refinement.api.datagen.patchouli.page.TextPage;
import com.ablackpikatchu.refinement.api.datagen.patchouli.type.PatchouliBook;
import com.ablackpikatchu.refinement.api.datagen.patchouli.type.PatchouliCategory;
import com.ablackpikatchu.refinement.api.datagen.patchouli.type.PatchouliEntry;
import com.ablackpikatchu.refinement.api.datagen.patchouli.vars.StringItemStack;
import com.ablackpikatchu.refinement.core.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class PatchouliProvider extends PatchouliGenProvider {

	@PatchouliBookGen
	public static final PatchouliBook BOOK = new PatchouliBook("Refinement Guide",
			"$(#800080)Welcome to Refinement $(playername)! $(br2)This book will guide you through the progression of the mod and show you all that you can do!")
					.setModel(new ResourceLocation(Refinement.MOD_ID, "refinement_guide"))
					.setTab("refinement_tools_weapons")
					.setBookTexture(new ResourceLocation("patchouli", "textures/gui/book_purple.png"))
					.setIndexIcon(Items.WRITABLE_BOOK).showProgress(false).setVersion(2).addDefaultMacros()
					.setCraftingTexture(new ResourceLocation(Refinement.MOD_ID, "textures/gui/patchouli/crafting.png"))
					.setHeaderColor("BF1DB8");

	public PatchouliProvider(DataGenerator generator) {
		super(generator, Refinement.MOD_ID, "en_us", "refinement_guide");
	}

	@PatchouliCategoryGen
	public static PatchouliCategory MACHINES_CATEGORY = new PatchouliCategory("Machines", "machines",
			"Information about the Refinement machines!", ItemInit.MACHINE_FRAME.get());
	
	@PatchouliCategoryGen
	public static PatchouliCategory MATERIALS_CATEGORY = new PatchouliCategory("Material's Index", "materials",
			"Here is a list of the different materials added into the game from refinement", Items.CRAFTING_TABLE);
	
	@PatchouliCategoryGen
	public static PatchouliCategory GENERAL_INFO_CATEGORY = new PatchouliCategory("General Info", "general_info",
			"Some general info about the mod. Learn about the Dev team with a link to their discord and information for modpack creators.", Items.BOOK);

	@PatchouliCategoryGen
	public static PatchouliCategory ARMOUR_CATEGORY = new PatchouliCategory("Armour Info", "armour",
			"Some info about the Refinement armour as is not your usual armour. Some of it can be upgraded!",
			ItemInit.Armor.REFINED_NETHERITE_CHESTPLATE);

	@PatchouliCategoryGen
	public static PatchouliCategory UPGRADES_CATEGORY = new PatchouliCategory("Upgrades", "upgrades",
			"Some info about the Refinement machine upgrades!", null)
					.setIcon(new StringItemStack(new ItemStack(ItemInit.SPEED_UPGRADE.get(), 8)));

	@Override
	public void addEntries() {
		entries.add(new PatchouliEntry(MACHINES_CATEGORY, "Fuel Usage", ItemInit.REFINED_CARBON_INGOT.get())
				.addPage(new TextPage("Fuel Types",
						"A Refinement machine can use one of 2 fuel types. $(b)FE$() or $(item)Refined Carbon Ingots$()."))
				.addPage(new SpotlightPage(ItemInit.REFINED_CARBON_INGOT.get(),
						"A machine that uses $()Refined Carbon Ingots$() as a fuel, will have a fuel slot in the bottom left corner. You can insert fuel into that slot through the sides of the machine, or by using an $(item)Auto Import Upgrade$()."))
				.addPage(new TextPage("Using FE as fuel",
						"A machine that uses FE as fuel will have a FE bar in the right side of the GUI. It will also have an info square which, when hovered over, will display information about the machine's energy usage, energy storage, etc. Energy can be inserted theru any side, using anything that outputs fuel.")));
		
		entries.add(new PatchouliEntry(UPGRADES_CATEGORY, "Energy Ability Upgrade", ItemInit.ENERGY_ABILITY_UPGRADE.get())
				.addPage(new SpotlightPage(ItemInit.ENERGY_ABILITY_UPGRADE.get(), "A machine usually runs off $(item)Refined Carbon Ingots$() but, some machines can be switched to using energy by installing an $(item)Energy Ability Upgrade$()."))
				.addPage(new CraftingRecipePage("refinement:machine_stuff/energy_ability_upgrade")));
	}

}
