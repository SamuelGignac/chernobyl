///**
// * Copyright (C) Glitchfiend
// * <p>
// * This program is free software; you can redistribute it and/or
// * modify it under the terms of the GNU Lesser General Public
// * License as published by the Free Software Foundation; either
// * version 3 of the License, or (at your option) any later version.
// * <p>
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// * Lesser General Public License for more details.
// * <p>
// * You should have received a copy of the GNU Lesser General Public License
// * along with this program; if not, write to the Free Software Foundation,
// * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
// */
//package com.example.TerraBlender.Common.src.main.java.terrablender.util;
//
//import com.example.TerraBlender.Common.src.main.java.terrablender.DimensionTypeTags;
//import com.example.TerraBlender.Common.src.main.java.terrablender.api.RegionType;
//import com.example.TerraBlender.Common.src.main.java.terrablender.api.Regions;
//import com.example.TerraBlender.Common.src.main.java.terrablender.core.TerraBlender;
//import com.example.TerraBlender.Common.src.main.java.terrablender.worldgen.IExtendedBiomeSource;
//import com.example.TerraBlender.Common.src.main.java.terrablender.worldgen.IExtendedNoiseGeneratorSettings;
//import com.example.TerraBlender.Common.src.main.java.terrablender.worldgen.IExtendedParameterList;
//import com.google.common.collect.ImmutableList;
//import net.minecraft.core.Holder;
//import net.minecraft.core.Registry;
//import net.minecraft.core.RegistryAccess;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeSource;
//import net.minecraft.world.level.biome.Climate;
//import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
//import net.minecraft.world.level.chunk.ChunkGenerator;
//import net.minecraft.world.level.dimension.DimensionType;
//import net.minecraft.world.level.dimension.LevelStem;
//import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
//import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
//
//import java.util.Map;
//
//public class LevelUtils
//{
//    public static void initializeOnServerStart(MinecraftServer server)
//    {
//        RegistryAccess registryAccess = server.registryAccess();
//        Registry<LevelStem> levelStemRegistry = registryAccess.registryOrThrow(Registries.LEVEL_STEM);
//        long seed = server.getWorldData().worldGenOptions().seed();
//
//        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : levelStemRegistry.entrySet())
//        {
//            LevelStem stem = entry.getValue();
//            initializeBiomes(registryAccess, stem.type(), entry.getKey(), stem.generator(), seed);
//        }
//    }
//
//    public static boolean shouldApplyToChunkGenerator(ChunkGenerator chunkGenerator)
//    {
//        return chunkGenerator instanceof NoiseBasedChunkGenerator && shouldApplyToBiomeSource(chunkGenerator.getBiomeSource());
//    }
//
//    public static boolean shouldApplyToBiomeSource(BiomeSource biomeSource)
//    {
//        return biomeSource instanceof MultiNoiseBiomeSource;
//    }
//
//    public static RegionType getRegionTypeForDimension(Holder<DimensionType> dimensionType)
//    {
//        if (dimensionType.is(DimensionTypeTags.NETHER_REGIONS)) return RegionType.NETHER;
//        else if (dimensionType.is(DimensionTypeTags.OVERWORLD_REGIONS)) return RegionType.OVERWORLD;
//        else return null;
//    }
//
//    public static void initializeBiomes(ServerLevel serverLevel, ChunkGenerator chunkGenerator, long seed) {
//        if (!shouldApplyToChunkGenerator(chunkGenerator))
//            return;
//
//        DimensionType dimensionType = serverLevel.dimension().dimensionType();
//        ResourceLocation levelResourceKey = serverLevel.dimension().getType().location();
//
//        // You can use the dimensionType and levelResourceKey as needed for your mod.
//
//        // Get the chunk generator settings
//        NoiseGeneratorSettings generatorSettings = chunkGenerator.getSettings();
//        if (generatorSettings instanceof NoiseBasedChunkGenerator.Settings) {
//            NoiseBasedChunkGenerator.Settings settings = (NoiseBasedChunkGenerator.Settings) generatorSettings;
//
//            // Get the biome source
//            MultiNoiseBiomeSource biomeSource = chunkGenerator.getBiomeSource();
//            if (biomeSource != null) {
//                // Do what you need with the biome source
//
//                // Initialize the parameter list for TerraBlender (if needed)
//                Climate.ParameterList parameters = biomeSource.getBiomeSource().parameters();
//
//                // Append modded biomes to the biome source biome list
//                Registry<Biome> biomeRegistry = serverLevel.registryAccess().registryOrThrow(BiomeRegistry.BIOME_REGISTRY);
//                ImmutableList.Builder<Supplier<Biome>> builder = ImmutableList.builder();
//
//                // Add your biomes here to the builder
//                builder.add(() -> biomeRegistry.getOrThrow(YourModBiomes.YOUR_BIOME));
//
//                biomeSource.addSupplier(builder.build());
//
//                // Log a message
//                TerraBlender.LOGGER.info("Initialized TerraBlender biomes for level stem " + levelResourceKey);
//            }
//        }
//    }
//
//    private static boolean shouldApplyToChunkGenerator(ChunkGenerator chunkGenerator) {
//        // Implement your logic to determine if the function should apply to the given chunk generator.
//        return chunkGenerator instanceof NoiseBasedChunkGenerator;
//    }
//
////    public static void initializeBiomes(RegistryAccess registryAccess, Holder<DimensionType> dimensionType, ResourceKey<LevelStem> levelResourceKey, ChunkGenerator chunkGenerator, long seed)
////    {
////        if (!shouldApplyToChunkGenerator(chunkGenerator))
////            return;
////
////        RegionType regionType = getRegionTypeForDimension(dimensionType);
////        NoiseBasedChunkGenerator noiseBasedChunkGenerator = (NoiseBasedChunkGenerator)chunkGenerator;
////        NoiseGeneratorSettings generatorSettings = noiseBasedChunkGenerator.generatorSettings().value();
////        MultiNoiseBiomeSource biomeSource = (MultiNoiseBiomeSource)chunkGenerator.getBiomeSource();
////        IExtendedBiomeSource biomeSourceEx = (IExtendedBiomeSource)biomeSource;
////
////        // Don't continue if region type is uninitialized
////        if (regionType == null)
////            return;
////
////        // Set the chunk generator settings' region type
////        ((IExtendedNoiseGeneratorSettings)(Object)generatorSettings).setRegionType(regionType);
////
////        Climate.ParameterList parameters = biomeSource.parameters();
////        IExtendedParameterList parametersEx = (IExtendedParameterList)parameters;
////
////        // Initialize the parameter list for TerraBlender
////        parametersEx.initializeForTerraBlender(registryAccess, regionType, seed);
////
////        // Append modded biomes to the biome source biome list
////        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);
////        ImmutableList.Builder<Holder<Biome>> builder = ImmutableList.builder();
////        Regions.get(regionType).forEach(region -> region.addBiomes(biomeRegistry, pair -> builder.add(biomeRegistry.getHolderOrThrow(pair.getSecond()))));
////        biomeSourceEx.appendDeferredBiomesList(builder.build());
////
////        TerraBlender.LOGGER.info(String.format("Initialized TerraBlender biomes for level stem %s", levelResourceKey.location()));
////    }
//}
