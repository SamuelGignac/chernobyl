
package com.example.chernobyl.api.biome;

import com.example.chernobyl.Chernobyl;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class CBiomes
{
    private static List<ResourceKey<Biome>> overworldBiomes = Lists.newArrayList();
    private static List<ResourceKey<Biome>> allBiomes = Lists.newArrayList();

    public static final ResourceKey<Biome> RADIOACTIVE = registerOverworld("radioactive");

    public static List<ResourceKey<Biome>> getOverworldBiomes()
    {
        return ImmutableList.copyOf(overworldBiomes);
    }

    public static List<ResourceKey<Biome>> getAllBiomes()
    {
        return ImmutableList.copyOf(allBiomes);
    }

    private static ResourceKey<Biome> registerOverworld(String name)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, new ResourceLocation(Chernobyl.MODID, name));
        overworldBiomes.add(key);
        allBiomes.add(key);
        return key;
    }

    private static ResourceKey<Biome> register(String name)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, new ResourceLocation(Chernobyl.MODID, name));
        allBiomes.add(key);
        return key;
    }
}
