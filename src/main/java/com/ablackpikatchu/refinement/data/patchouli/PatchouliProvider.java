package com.ablackpikatchu.refinement.data.patchouli;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.core.init.ItemInit;
import com.ablackpikatchu.refinement.data.patchouli.builder.PatchouliGenProvider;
import com.ablackpikatchu.refinement.data.patchouli.builder.page.SpotlightPage;
import com.ablackpikatchu.refinement.data.patchouli.builder.page.TextPage;
import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliBook;
import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliCategory;
import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliEntry;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

public class PatchouliProvider extends PatchouliGenProvider {

	@PatchouliBookGen
	public static PatchouliBook BOOK = new PatchouliBook("Refinement Guide",
			"$(#800080)Welcome to Refinement $(playername)! $(br2)This book will guide you through the progression of the mod and show you all that you can do!")
					.setModel(new ResourceLocation(Refinement.MOD_ID, "refinement_guide"))
					.setTab("refinement_tools_weapons")
					.setBookTexture(new ResourceLocation("patchouli", "textures/gui/book_purple.png"))
					.setIndexIcon(Items.WRITABLE_BOOK).showProgress(false).setVersion(2).addDefaultMacros()
					.setCraftingTexture(new ResourceLocation(Refinement.MOD_ID, "textures/gui/patchouli/crafting.png"));

	@PatchouliCategoryGen
	public static PatchouliCategory MACHINES_CATEGORY = new PatchouliCategory("Machines", "machines",
			"Information about the Refinement machines!", ItemInit.MACHINE_FRAME.get());

	public PatchouliProvider(DataGenerator generator) {
		super(generator, Refinement.MOD_ID, "en_us", "refinement_guide");
	}

	@Override
	public void addEntries() {
		entries.add(new PatchouliEntry(MACHINES_CATEGORY, "Fuel Usage", ItemInit.REFINED_CARBON_INGOT.get())
				.addPage(new TextPage("Fuel Types",
						"A Refinement machine can use one of 2 fuel types. $(b)FE$() or $(item)Refined Carbon Ingots$()."))
				.addPage(new SpotlightPage(ItemInit.REFINED_CARBON_INGOT.get(),
						"A machine that uses $()Refined Carbon Ingots$() as a fuel, will have a fuel slot in the bottom left corner. You can insert fuel into that slot through the sides of the machine, or by using an $(item)Auto Import Upgrade$()."))
				.addPage(new TextPage("Using FE as fuel",
						"A machine that uses FE as fuel will have a FE bar in the right side of the GUI. It will also have an info square which, when hovered over, will display information about the machine's energy usage, energy storage, etc. Energy can be inserted theru any side, using anything that outputs fuel.")));
	}

	@Override
	public void addCategories() {

	}

}