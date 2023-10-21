package com.example.chernobyl.worldgen.feature;

import com.example.chernobyl.api.CBlocks;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

import static com.example.chernobyl.util.worldgen.CFeatureUtils.createKey;

public class CMiscOverworldFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_SAND_SPLATTER = createKey("black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_SPINE = createKey("bone_spine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_SPLATTER = createKey("crag_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_BLACK_SAND = createKey("disk_black_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_CALCITE = createKey("disk_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_GRAVEL_EXTRA = createKey("disk_gravel_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_ORANGE_SAND = createKey("disk_orange_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND = createKey("disk_white_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND_EXTRA = createKey("disk_white_sand_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_MUD = createKey("disk_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BLACK_SAND_SPLATTER = createKey("mossy_black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_SPLATTER = createKey("mud_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LAKE = createKey("water_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_LAKE_VOLCANO = createKey("lava_lake_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANO = createKey("spring_lava_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_WATER_EXTRA = createKey("spring_water_extra");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, CMiscOverworldFeatures.BLACK_SAND_SPLATTER, CBaseFeatures.BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CMiscOverworldFeatures.BONE_SPINE, CBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
        register(context, CMiscOverworldFeatures.CRAG_SPLATTER, CBaseFeatures.CRAG_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CMiscOverworldFeatures.DISK_BLACK_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(CBlocks.URANIUM_ORE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, CMiscOverworldFeatures.DISK_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.SAND, Blocks.GRAVEL, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(3, 7), 2));
        register(context, CMiscOverworldFeatures.DISK_GRAVEL_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(1, 4), 2));
        register(context, CMiscOverworldFeatures.DISK_ORANGE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(CBlocks.URANIUM_ORE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, CMiscOverworldFeatures.DISK_WHITE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(CBlocks.URANIUM_ORE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, CMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(CBlocks.URANIUM_ORE.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 1));
        register(context, CMiscOverworldFeatures.DISK_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(4, 6), 2));
        register(context, CMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER, CBaseFeatures.MOSSY_BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CMiscOverworldFeatures.MUD_SPLATTER, CBaseFeatures.MUD_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CMiscOverworldFeatures.WATER_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, CMiscOverworldFeatures.LAVA_LAKE_VOLCANO, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, CMiscOverworldFeatures.SPRING_LAVA_VOLCANO, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.MAGMA_BLOCK, CBlocks.URANIUM_ORE.get(), CBlocks.URANIUM_ORE.get(), Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE)));
        register(context, CMiscOverworldFeatures.SPRING_WATER_EXTRA, Feature.SPRING, new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, CBlocks.URANIUM_ORE.get())));
    }
    
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
