
package com.example.chernobyl.init;

import com.example.TerraBlender.Common.src.main.java.terrablender.api.Regions;
import com.example.TerraBlender.Common.src.main.java.terrablender.api.SurfaceRuleManager;
import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.api.CBiomes;
import com.example.chernobyl.biome.COverworldBiomes;
import com.example.chernobyl.worldgen.COverworldRegionPrimary;
import com.example.chernobyl.worldgen.COverworldRegionRare;
import com.example.chernobyl.worldgen.COverworldRegionSecondary;
import com.example.chernobyl.worldgen.CSurfaceRuleData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes
{
    public static void setup()
    {
        // Register our biomes
//        registerVillagerTypes();
    }

    public static void setupTerraBlender()
    {
        // Register our regions
        Regions.register(new COverworldRegionPrimary(ModConfig.GenerationConfig.bopPrimaryOverworldRegionWeight.get()));
        Regions.register(new COverworldRegionSecondary(ModConfig.GenerationConfig.bopSecondaryOverworldRegionWeight.get()));
        Regions.register(new COverworldRegionRare(ModConfig.GenerationConfig.bopOverworldRareRegionWeight.get()));

        // Register our surface rules
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Chernobyl.MODID, CSurfaceRuleData.overworld());
    }

    public static void bootstrapBiomes(BootstapContext<Biome> context)
    {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        register(context, CBiomes.RADIOACTIVE, COverworldBiomes.auroralGarden(placedFeatureGetter, carverGetter));
    }

    private static void register(BootstapContext<Biome> context, ResourceKey<Biome> key, Biome biome)
    {
        context.register(key, biome);
    }

    private static void registerVillagerTypes()
    {
//        registerVillagerType(CBiomes.RADIOACTIVE, VillagerType.ALL);
    }

    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
//            VillagerType.byBiome(key, type);
        }
    }

}
