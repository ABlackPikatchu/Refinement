package com.ablackpikatchu.refinement.common.command.quote;

import static com.ablackpikatchu.refinement.Refinement.MOD_ID;

import java.util.LinkedList;
import java.util.List;

import com.ablackpikatchu.refinement.Refinement;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import net.minecraftforge.common.util.Constants;

public class QuoteManager extends WorldSavedData {
	
	private static final String NAME = MOD_ID + "_quotes";

	private final World world;
	private final List<Quote> quotes = new LinkedList<>();
	
	public QuoteManager(String name, World world) {
		super(name);
		this.world = world;
	}
	
	public static QuoteManager get(World world) {
        return get((ServerWorld) world);
    }

    public static QuoteManager get(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(() -> new QuoteManager(NAME, world), NAME);
    }
    
    public void addQuote(Quote quote) {
    	quotes.add(quote);
    	setDirty();
    }
    
    public void removeQuote(int index) {
    	if (quotes.size() - 1 >= index) {
    		quotes.remove(index);
    		setDirty();
    	} else
    		Refinement.LOGGER.warn("Error while trying to remove a quote! Quote {} does not exist", index);
    }
    
    public int getQuotesNumber() { return quotes.size(); }
    
    public boolean quoteExists(int index) { return index <= (quotes.size() - 1); }
    
    public Quote getQuote(int index) {return quotes.get(index); }
    
	@Override
	public void load(CompoundNBT tag) {
		ListNBT quotes = tag.getList("quotes", Constants.NBT.TAG_COMPOUND);
        for (INBT quote : quotes) {
            CompoundNBT quoteNbt = (CompoundNBT) quote;
            this.quotes.add(Quote.fromNbt(quoteNbt));
        }
	}

	@Override
	public CompoundNBT save(CompoundNBT tag) {
		ListNBT quotes = new ListNBT();
		this.quotes.forEach(quote -> quotes.add(quote.toNbt()));
		tag.put("quotes", quotes);
        return tag;
	}
	
	public World getWorld() {
		return world;
	}

}
