package com.example.chernobyl.init;

import com.example.chernobyl.recipes.ModEventSubscriber;

public class ModRecipe {

    public static void setup() {
        ModEventSubscriber.registerRecipeSerializers();
        ModEventSubscriber.registerRecipeTypes();
    }

}
