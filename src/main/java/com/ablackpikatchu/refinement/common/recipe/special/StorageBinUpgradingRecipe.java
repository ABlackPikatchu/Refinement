package com.ablackpikatchu.refinement.common.recipe.special;

import java.util.Map;

import com.ablackpikatchu.refinement.Refinement;
import com.ablackpikatchu.refinement.common.inventory.StorageBinHandler;
import com.ablackpikatchu.refinement.common.item.blockitem.StorageBinBlockItem;
import com.ablackpikatchu.refinement.common.te.misc_tes.StorageBinTileEntity;
import com.ablackpikatchu.refinement.core.util.helper.NBTHelper;
import com.google.gson.JsonObject;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class StorageBinUpgradingRecipe implements ICraftingRecipe, IShapedRecipe<CraftingInventory> {

	public static final Serializer SERIALIZER = new Serializer();

	private final ShapedRecipe internal;
	private final int oldBinSlot;

	public StorageBinUpgradingRecipe(ShapedRecipe internal, int oldBinSlot) {
		this.internal = internal;
		this.oldBinSlot = oldBinSlot;
	}

	public ShapedRecipe getInternal() {
		return internal;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public boolean matches(CraftingInventory inv, World world) {
		return internal.matches(inv, world) && !assemble(inv).isEmpty();
	}

	@Override
	public ItemStack assemble(CraftingInventory pInv) {
		if (pInv.getItem(oldBinSlot).getItem() instanceof StorageBinBlockItem) {
			if (internal.getResultItem().getItem() instanceof StorageBinBlockItem) {
				ItemStack stack = internal.getResultItem().copy();
				StorageBinHandler handler = StorageBinTileEntity.handlerFromNbt(pInv.getItem(oldBinSlot).getOrCreateTag());
				handler.setStackLimit(((StorageBinBlockItem) internal.getResultItem().getItem()).getStackLimit());
				StorageBinTileEntity.handlerToNbt(handler, stack.getOrCreateTag());
				NBTHelper.setBoolean(stack, "RefillEnabled", NBTHelper.getBoolean(pInv.getItem(oldBinSlot), "RefillEnabled"));
				return stack;
			}
		}
		return internal.getResultItem().copy();
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return internal.canCraftInDimensions(pWidth, pHeight);
	}

	@Override
	public ItemStack getResultItem() {
		return internal.getResultItem().copy();
	}

	@Override
	public ResourceLocation getId() {
		return internal.getId();
	}

	@Override
	public int getRecipeWidth() {
		return internal.getRecipeWidth();
	}

	@Override
	public int getRecipeHeight() {
		return internal.getRecipeHeight();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return internal.getIngredients();
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<StorageBinUpgradingRecipe> {

		public Serializer() {
			this.setRegistryName(new ResourceLocation(Refinement.MOD_ID, "storage_bin_upgrade"));
		}

		@Override
		public StorageBinUpgradingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String s = JSONUtils.getAsString(json, "group", "");
			Map<String, Ingredient> map = ShapedRecipe.keyFromJson(JSONUtils.getAsJsonObject(json, "key"));
			String[] astring = ShapedRecipe.patternFromJson(JSONUtils.getAsJsonArray(json, "pattern"));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<Ingredient> nonnulllist = ShapedRecipe.dissolvePattern(astring, map, i, j);
			ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
			int oldBinSlot = json.has("old_bin_slot") ? json.get("old_bin_slot").getAsInt() : 4;
			return new StorageBinUpgradingRecipe(new ShapedRecipe(recipeId, s, i, j, nonnulllist, itemstack), oldBinSlot);
		}

		@Override
		public StorageBinUpgradingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			int i = buffer.readVarInt();
			int j = buffer.readVarInt();
			String s = buffer.readUtf(32767);
			NonNullList<Ingredient> inputs = NonNullList.withSize(i * j, Ingredient.EMPTY);

			for (int k = 0; k < inputs.size(); ++k) {
				inputs.set(k, Ingredient.fromNetwork(buffer));
			}

			ItemStack output = buffer.readItem();
			int oldBinSlot = buffer.readInt();
			
			return new StorageBinUpgradingRecipe(new ShapedRecipe(recipeId, s, i, j, inputs, output), oldBinSlot);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, StorageBinUpgradingRecipe recipe) {
			buffer.writeVarInt(recipe.getRecipeWidth());
			buffer.writeVarInt(recipe.getRecipeHeight());
			buffer.writeUtf(recipe.getGroup());

			for (Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
			buffer.writeInt(recipe.oldBinSlot);
		}
	}
	
	public static class StorageBinUpgradingRecipeType implements IRecipeType<StorageBinUpgradingRecipe> {
		@Override
		public String toString() {
			return new ResourceLocation(Refinement.MOD_ID, "storage_bin_upgrade").toString();
		}
	}

}
