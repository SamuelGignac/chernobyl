package com.example.chernobyl.recipes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class CRecipe implements Recipe<Container> {

    private final Ingredient input; // The input ingredient for the recipe.
    private final int data; // Additional data for the recipe, if needed.
    private final ItemStack output; // The output ItemStack of the recipe.

    public CRecipe(Ingredient input, int data, ItemStack output) {
        this.input = input;
        this.data = data;
        this.output = output;
    }

    @Override
    public boolean matches(Container p_44002_, Level p_44003_) {
        return true;
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return false;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return null;
    }
}
