
package com.example.chernobyl.worldgen.placement;

import com.example.chernobyl.worldgen.feature.CCaveFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static com.example.chernobyl.util.worldgen.CPlacementUtils.createKey;

public class CCavePlacements
{
    public static final ResourceKey<PlacedFeature> GLOWING_GROTTO_VEGETATION = createKey("glowing_grotto_vegetation");
    public static final ResourceKey<PlacedFeature> GLOWING_GROTTO_MUD = createKey("glowing_grotto_mud");
    public static final ResourceKey<PlacedFeature> GLOWWORM_SILK_STRANDS = createKey("glowworm_silk_strands");
    public static final ResourceKey<PlacedFeature> GIANT_GLOWSHROOM_CAVE = createKey("giant_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> HUGE_GLOWSHROOM_CAVE = createKey("huge_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> MEDIUM_GLOWSHROOM_CAVE = createKey("medium_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> SMALL_GLOWSHROOM_CAVE = createKey("small_glowshroom_cave");
    public static final ResourceKey<PlacedFeature> EXTRA_GLOW_LICHEN = createKey("extra_glow_lichen");
    public static final ResourceKey<PlacedFeature> HANGING_COBWEBS = createKey("hanging_cobwebs");
    public static final ResourceKey<PlacedFeature> CORNER_COBWEBS = createKey("corner_cobwebs");
    public static final ResourceKey<PlacedFeature> SPIDER_EGGS = createKey("spider_eggs");
    public static final ResourceKey<PlacedFeature> STRINGY_COBWEB = createKey("stringy_cobweb");
    public static final ResourceKey<PlacedFeature> WEBBING = createKey("webbing");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> WEBBING = configuredFeatureGetter.getOrThrow(CCaveFeatures.WEBBING);
        final Holder<ConfiguredFeature<?, ?>> STRINGY_COBWEB = configuredFeatureGetter.getOrThrow(CCaveFeatures.STRINGY_COBWEB);
        final Holder<ConfiguredFeature<?, ?>> SPIDER_EGG = configuredFeatureGetter.getOrThrow(CCaveFeatures.SPIDER_EGG);
        final Holder<ConfiguredFeature<?, ?>> CORNER_COBWEBS = configuredFeatureGetter.getOrThrow(CCaveFeatures.CORNER_COBWEBS);
        final Holder<ConfiguredFeature<?, ?>> HANGING_COBWEB = configuredFeatureGetter.getOrThrow(CCaveFeatures.HANGING_COBWEB);
        final Holder<ConfiguredFeature<?, ?>> EXTRA_GLOW_LICHEN = configuredFeatureGetter.getOrThrow(CCaveFeatures.EXTRA_GLOW_LICHEN);
        final Holder<ConfiguredFeature<?, ?>> SMALL_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(CCaveFeatures.SMALL_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> MEDIUM_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(CCaveFeatures.MEDIUM_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> HUGE_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(CCaveFeatures.HUGE_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> GIANT_GLOWSHROOM_CAVE = configuredFeatureGetter.getOrThrow(CCaveFeatures.GIANT_GLOWSHROOM_CAVE);
        final Holder<ConfiguredFeature<?, ?>> GLOWWORM_SILK = configuredFeatureGetter.getOrThrow(CCaveFeatures.GLOWWORM_SILK);
        final Holder<ConfiguredFeature<?, ?>> MUD_PATCH = configuredFeatureGetter.getOrThrow(CCaveFeatures.MUD_PATCH);
        final Holder<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH = configuredFeatureGetter.getOrThrow(CCaveFeatures.GLOWING_MOSS_PATCH);

        register(context, CCavePlacements.GLOWING_GROTTO_VEGETATION, GLOWING_MOSS_PATCH, List.of(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.GLOWING_GROTTO_MUD, MUD_PATCH, List.of(CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.GLOWWORM_SILK_STRANDS, GLOWWORM_SILK, List.of(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
        register(context, CCavePlacements.GIANT_GLOWSHROOM_CAVE, GIANT_GLOWSHROOM_CAVE, List.of(CountPlacement.of(50), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.HUGE_GLOWSHROOM_CAVE, HUGE_GLOWSHROOM_CAVE, List.of(CountPlacement.of(75), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.MEDIUM_GLOWSHROOM_CAVE, MEDIUM_GLOWSHROOM_CAVE, List.of(CountPlacement.of(100), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.SMALL_GLOWSHROOM_CAVE, SMALL_GLOWSHROOM_CAVE, List.of(CountPlacement.of(125), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.EXTRA_GLOW_LICHEN, EXTRA_GLOW_LICHEN, List.of(CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
        register(context, CCavePlacements.HANGING_COBWEBS, HANGING_COBWEB, List.of(CountPlacement.of(200), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome()));
        register(context, CCavePlacements.CORNER_COBWEBS, CORNER_COBWEBS, List.of(CountPlacement.of(40), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 24), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.SPIDER_EGGS, SPIDER_EGG, List.of(CountPlacement.of(35), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.STRINGY_COBWEB, STRINGY_COBWEB, List.of(CountPlacement.of(150), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), RandomOffsetPlacement.vertical(ConstantInt.of(1)), BiomeFilter.biome()));
        register(context, CCavePlacements.WEBBING, WEBBING, List.of(CountPlacement.of(18), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
