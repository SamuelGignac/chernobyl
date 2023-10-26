package com.example.chernobyl.init;

import com.example.chernobyl.recipes.CRecipeProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModRecipes {
    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(
                event.includeServer(),
                new CRecipeProvider(event.getGenerator().getPackOutput())
        );
    }
}
