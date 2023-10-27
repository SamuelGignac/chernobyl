package com.example.chernobyl.recipes;

import com.example.chernobyl.Chernobyl;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModEventSubscriber {
        public static void registerRecipeSerializers() {
//        CRecipeSerializer.INSTANCE = Registry.register(Registries.RECIPE_SERIALIZER, new ResourceLocation(Chernobyl.MODID, "c_recipe_serializer"), new CRecipeSerializer());
        }

        public static class RecipeTypes {
                public static final RegistryObject<RecipeType<CRecipe>> NUCLEAR_BOMB = null;
        }

        public static void registerRecipeTypes() {
//        RecipeTypes.NUCLEAR_BOMB = Registry.register(Registries.RECIPE_TYPE, new ResourceLocation(Chernobyl.MODID, "nuclear_bomb"), new CRecipeSerializer());
        }
}
