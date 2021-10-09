package com.ablackpikatchu.refinement.data.patchouli.builder;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliBook;
import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliCategory;
import com.ablackpikatchu.refinement.data.patchouli.builder.type.PatchouliEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;

public abstract class PatchouliGenProvider implements IDataProvider {

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private static final Logger LOGGER = LogManager.getLogger();
	public final DataGenerator generator;

	public final String modid;
	public final String language;
	public final String bookName;

	public ArrayList<PatchouliEntry> entries = new ArrayList<>();
	public ArrayList<PatchouliCategory> categories = new ArrayList<>();

	public PatchouliGenProvider(DataGenerator generator, String modid, String language, String bookName) {
		this.generator = generator;
		this.modid = modid;
		this.language = language;
		this.bookName = bookName;
	}

	@Override
	public void run(DirectoryCache pCache) throws IOException {
		try {
			writeBook(pCache);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		addEntries();
		writeEntries(pCache);

		addCategories();
		try {
			writeCategories(pCache);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeEntries(DirectoryCache cache) {
		Path outputFolder = this.generator.getOutputFolder();
		entries.forEach(entry -> {
			Path path = outputFolder.resolve("data/" + modid + "/patchouli_books/" + bookName + "/" + language
					+ "/entries/" + entry.category + "/" + entry.fileName + ".json");
			try {
				IDataProvider.save(GSON, cache, entry.serialize(), path);
			} catch (IOException e) {
				LOGGER.error("Couldn't generate entry!", path, (Object) e);
			}
		});
	}

	private void writeCategories(DirectoryCache cache) throws Exception {
		Path outputFolder = this.generator.getOutputFolder();
		Class<?> clazz = this.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(PatchouliCategoryGen.class)) {
				field.setAccessible(true);
				if (field.getType() == PatchouliCategory.class) {
					PatchouliCategory category = (PatchouliCategory) field.get(clazz);
					Path path = outputFolder.resolve("data/" + modid + "/patchouli_books/" + bookName + "/" + language
							+ "/categories/" + category.fileName + ".json");
					try {
						IDataProvider.save(GSON, cache, category.serialize(), path);
					} catch (IOException e) {
						LOGGER.error("Couldn't generate category!", path, (Object) e);
					}
				}
			}
		}
		categories.forEach(category -> {
			Path path = outputFolder.resolve("data/" + modid + "/patchouli_books/" + bookName + "/" + language
					+ "/categories/" + category.fileName + ".json");
			try {
				IDataProvider.save(GSON, cache, category.serialize(), path);
			} catch (IOException e) {
				LOGGER.error("Couldn't generate category!", path, (Object) e);
			}
		});
	}

	private void writeBook(DirectoryCache cache) throws Exception {
		Path outputFolder = this.generator.getOutputFolder();
		Class<?> clazz = this.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(PatchouliBookGen.class)) {
				field.setAccessible(true);
				if (field.getType() == PatchouliBook.class) {
					PatchouliBook book = (PatchouliBook) field.get(clazz);
					Path path = outputFolder
							.resolve("data/" + modid + "/patchouli_books/" + bookName + "/" + "book.json");
					try {
						IDataProvider.save(GSON, cache, book.serialize(), path);
					} catch (IOException e) {
						LOGGER.error("Couldn't generate book!", path, (Object) e);
					}
				}
			}
		}
	}

	@Nullable
	public abstract void addEntries();

	@Nullable
	public abstract void addCategories();

	@Override
	public String getName() {
		return "PatchouliGenProvider";
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	protected @interface PatchouliCategoryGen {

	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	protected @interface PatchouliBookGen {

	}

}
