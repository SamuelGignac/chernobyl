package com.example.chernobyl.recipes;

import com.example.chernobyl.api.CBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

//public class CRecipeProvider extends RecipeProvider {
//    public CRecipeProvider(PackOutput p_248933_) {
//        super(p_248933_);
//    }
//
//    @Override
//    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> p_251297_) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, CBlocks.NUCLEAR_BOMB.get(), 1)
//                .pattern("III")
//                .pattern("RTR")
//                .pattern("III")
//                .define('I', Ingredient.of(Items.IRON_INGOT))
//                .define('R', Ingredient.of(Items.REDSTONE_BLOCK))
//                .define('T', Ingredient.of(Items.TNT))
//                .save(p_251297_);
//
//    }
//}
