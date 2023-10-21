
package com.example.chernobyl.worldgen;

import com.example.TerraBlender.Common.src.main.java.terrablender.api.Region;
import com.example.TerraBlender.Common.src.main.java.terrablender.api.RegionType;
import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.biome.COverworldBiomeBuilder;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class COverworldRegionPrimary extends Region
{
    public static final ResourceLocation LOCATION = new ResourceLocation(Chernobyl.MODID, "overworld_primary");

    public COverworldRegionPrimary(int weight)
    {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new COverworldBiomeBuilder()).addBiomes(registry, mapper);
    }
}
