package com.example.chernobyl.recipes;

import com.example.chernobyl.api.CBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class CRecipeVanillaProvider extends VanillaRecipeProvider {

    public CRecipeVanillaProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        super.buildRecipes(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, CBlocks.NUCLEAR_BOMB.get(), 1)
                .pattern("III")
                .pattern("RTR")
                .pattern("III")
                .define('I', Ingredient.of(Items.IRON_INGOT, Blocks.IRON_BLOCK))
                .define('R', Ingredient.of(Items.REDSTONE_BLOCK))
                .define('T', Ingredient.of(Items.TNT))
                .unlockedBy("has_tnt", has(Items.TNT))
                .save(consumer);
    }
}
