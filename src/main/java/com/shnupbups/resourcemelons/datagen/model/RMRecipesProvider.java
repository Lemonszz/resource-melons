package com.shnupbups.resourcemelons.datagen.model;

import java.util.function.Consumer;

import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonFactory;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Items;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipesProvider;

import com.shnupbups.resourcemelons.RMCommon;
import com.shnupbups.resourcemelons.core.MelonType;

public class RMRecipesProvider extends FabricRecipesProvider {
	public RMRecipesProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	public static void offerRecipes(MelonType melonType, Consumer<RecipeJsonProvider> exporter) {
		createSeedsRecipe(melonType).criterion(RecipesProvider.hasItem(melonType.resourceBlock()), RecipesProvider.conditionsFromItem(melonType.resourceBlock())).offerTo(exporter);
		createAltSeedsRecipe(melonType).criterion(RecipesProvider.hasItem(melonType.resourceBlock()), RecipesProvider.conditionsFromItem(melonType.resourceBlock())).offerTo(exporter, CraftingRecipeJsonFactory.getItemId(melonType.seeds())+"_alt");
		createSeedsFromMelonRecipe(melonType).criterion(RecipesProvider.hasItem(melonType.melon()), RecipesProvider.conditionsFromItem(melonType.melon())).offerTo(exporter, CraftingRecipeJsonFactory.getItemId(melonType.seeds())+"_from_melon");
		createMelonRecipe(melonType).criterion(RecipesProvider.hasItem(melonType.slice()), RecipesProvider.conditionsFromItem(melonType.slice())).offerTo(exporter);
	}

	public static CraftingRecipeJsonFactory createSeedsRecipe(MelonType melonType) {
		return ShapedRecipeJsonFactory.create(melonType.seeds()).input('r', melonType.resource()).input('b', melonType.resourceBlock()).input('s', Items.MELON_SEEDS).pattern("rbr").pattern("bsb").pattern("rbr").group(CraftingRecipeJsonFactory.getItemId(melonType.seeds()).getPath());
	}

	public static CraftingRecipeJsonFactory createAltSeedsRecipe(MelonType melonType) {
		return ShapedRecipeJsonFactory.create(melonType.seeds()).input('r', melonType.resource()).input('b', melonType.resourceBlock()).input('s', Items.MELON_SEEDS).pattern("brb").pattern("rsr").pattern("brb").group(CraftingRecipeJsonFactory.getItemId(melonType.seeds()).getPath());
	}

	public static CraftingRecipeJsonFactory createMelonRecipe(MelonType melonType) {
		return ShapedRecipeJsonFactory.create(melonType.melon()).input('s', melonType.slice()).pattern("sss").pattern("sss").pattern("sss");
	}

	public static CraftingRecipeJsonFactory createSeedsFromMelonRecipe(MelonType melonType) {
		return ShapelessRecipeJsonFactory.create(melonType.seeds()).input(melonType.melon());
	}

	@Override
	protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
		RMCommon.LOGGER.info("Generating recipes...");

		for (MelonType melonType : RMCommon.MELONS) {
			RMCommon.LOGGER.info("Generating for " + melonType.id());

			offerRecipes(melonType, exporter);
		}

		RMCommon.LOGGER.info("Finished generating recipes!");
	}
}
