package com.example.chernobyl.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class CRecipeSerializer implements RecipeSerializer<CRecipe> {
    public static CRecipeSerializer INSTANCE;

    @Override
    public CRecipe fromJson(ResourceLocation p_44103_, JsonObject p_44104_) {
        return null;
    }

    @Override
    public @Nullable CRecipe fromNetwork(ResourceLocation p_44105_, FriendlyByteBuf p_44106_) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf p_44101_, CRecipe p_44102_) {

    }
}
