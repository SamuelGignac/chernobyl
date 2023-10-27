package com.example.chernobyl.recipes;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class ModEventSubscriber {
        public static void registerRecipeSerializers() {
//        CRecipeSerializer.INSTANCE = Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(Chernobyl.MODID, "c_recipe_serializer"), new CRecipeSerializer());
        }

        public static class RecipeTypes {
                public static final RegistryObject<RecipeType<CRecipe>> NUCLEAR_BOMB = null;
        }

        public static void registerRecipeTypes() {
//        RecipeTypes.NUCLEAR_BOMB = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Chernobyl.MODID, "nuclear_bomb"), new CRecipeSerializer());
        }
}
