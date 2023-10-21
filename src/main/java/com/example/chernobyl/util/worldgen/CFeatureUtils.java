
package com.example.chernobyl.util.worldgen;

import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.worldgen.feature.CCaveFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CFeatureUtils
{
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        CCaveFeatures.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Chernobyl.MODID, name));
    }
}
