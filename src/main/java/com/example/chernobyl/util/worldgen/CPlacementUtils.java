
package com.example.chernobyl.util.worldgen;

import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.worldgen.placement.CCavePlacements;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class CPlacementUtils
{
    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        CCavePlacements.bootstrap(context);
    }

    public static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Chernobyl.MODID, name));
    }
}
