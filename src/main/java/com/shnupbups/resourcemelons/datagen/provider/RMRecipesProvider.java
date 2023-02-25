package com.shnupbups.resourcemelons.datagen.provider;

import java.util.function.Consumer;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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
		createSeedsRecipe(melonType).criterion(RecipeProvider.hasItem(melonType.resourceBlock()), RecipeProvider.conditionsFromItem(melonType.resourceBlock())).offerTo(exporter);
		createAltSeedsRecipe(melonType).criterion(RecipeProvider.hasItem(melonType.resourceBlock()), RecipeProvider.conditionsFromItem(melonType.resourceBlock())).offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(melonType.seeds()) + "_alt");
		createSeedsFromMelonRecipe(melonType).criterion(RecipeProvider.hasItem(melonType.melon()), RecipeProvider.conditionsFromItem(melonType.melon())).offerTo(exporter, CraftingRecipeJsonBuilder.getItemId(melonType.seeds()) + "_from_melon");
		createMelonRecipe(melonType).criterion(RecipeProvider.hasItem(melonType.slice()), RecipeProvider.conditionsFromItem(melonType.slice())).offerTo(exporter);
	}

	public static CraftingRecipeJsonBuilder createSeedsRecipe(MelonType melonType) {
		return ShapedRecipeJsonBuilder.create(melonType.seeds()).input('r', melonType.resource()).input('b', melonType.resourceBlock()).input('s', Items.MELON_SEEDS).pattern("rbr").pattern("bsb").pattern("rbr").group(CraftingRecipeJsonBuilder.getItemId(melonType.seeds()).getPath());
	}

	public static CraftingRecipeJsonBuilder createAltSeedsRecipe(MelonType melonType) {
		return ShapedRecipeJsonBuilder.create(melonType.seeds()).input('r', melonType.resource()).input('b', melonType.resourceBlock()).input('s', Items.MELON_SEEDS).pattern("brb").pattern("rsr").pattern("brb").group(CraftingRecipeJsonBuilder.getItemId(melonType.seeds()).getPath());
	}

	public static CraftingRecipeJsonBuilder createMelonRecipe(MelonType melonType) {
		return ShapedRecipeJsonBuilder.create(melonType.melon()).input('s', melonType.slice()).pattern("sss").pattern("sss").pattern("sss");
	}

	public static CraftingRecipeJsonBuilder createSeedsFromMelonRecipe(MelonType melonType) {
		return ShapelessRecipeJsonBuilder.create(melonType.seeds()).input(melonType.melon());
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
