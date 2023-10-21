package com.example.chernobyl.worldgen.feature;

import com.example.chernobyl.api.CBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.example.chernobyl.util.worldgen.CFeatureUtils.createKey;

public class CVegetationFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGH_GRASS = createKey("high_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_DRIPLEAF = createKey("big_dripleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_PUMPKIN = createKey("big_pumpkin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRAMBLE = createKey("bramble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLD_DESERT_ROCKS = createKey("cold_desert_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_LOG = createKey("fallen_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_FIR_LOG = createKey("fallen_fir_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD = createKey("goldenrod");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_CLOVER = createKey("huge_clover");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_LILY_PAD = createKey("huge_lily_pad");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_TOADSTOOL = createKey("huge_toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIPSTONE_SPLATTER = createKey("dripstone_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_SPLATTER = createKey("moss_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIUM_SPLATTER = createKey("mycelium_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BARLEY = createKey("patch_barley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUE_HYDRANGEA = createKey("patch_blue_hydrangea");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BUSH = createKey("patch_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CATTAIL = createKey("patch_cattail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CLOVER = createKey("patch_clover");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_GRASS = createKey("patch_dead_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DESERT_GRASS = createKey("patch_desert_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DUNE_GRASS = createKey("patch_dune_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FERN = createKey("patch_fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ICY_IRIS = createKey("patch_icy_iris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LILAC = createKey("patch_lilac");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PEONY = createKey("patch_peony");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_REED = createKey("patch_reed");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SEA_OATS = createKey("patch_sea_oats");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SPROUTS = createKey("patch_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_LAVENDER = createKey("patch_tall_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TUNDRA_SHRUBS = createKey("patch_tundra_shrubs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATERGRASS = createKey("patch_watergrass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATERLILY_FLOWER = createKey("patch_waterlily_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PUMPKIN_PATCH = createKey("pumpkin_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINFOREST_CLIFFS_VINES = createKey("rainforest_cliffs_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROOTED_STUMP = createKey("rooted_stump");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_BUSH = createKey("rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCATTERED_ROCKS = createKey("scattered_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCRUB = createKey("scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNFLOWER = createKey("sunflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHORT_BAMBOO = createKey("short_bamboo");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_DRIPLEAF = createKey("small_dripleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BROWN_MUSHROOM = createKey("small_brown_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_RED_MUSHROOM = createKey("small_red_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_TOADSTOOL = createKey("small_toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOADSTOOL_NORMAL = createKey("toadstool_normal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WASTELAND_GRASS = createKey("wasteland_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_CLOVER_PATCH = createKey("flower_clover_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_CONIFEROUS_FOREST = createKey("flower_coniferous_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_DEFAULT_EXTENDED = createKey("flower_default_extended");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FIELD_1 = createKey("flower_field_1");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FIELD_2 = createKey("flower_field_2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_GRASSLAND = createKey("flower_grassland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_LAVENDER = createKey("flower_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_MOOR = createKey("flower_moor");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_MYSTIC_GROVE = createKey("flower_mystic_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_OMINOUS_WOODS = createKey("flower_ominous_woods");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_ORIGIN_VALLEY = createKey("flower_origin_valley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_POPPY = createKey("flower_poppy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_RAINFOREST = createKey("flower_rainforest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SHRUBLAND = createKey("flower_shrubland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SNOWBLOSSOM_GROVE = createKey("flower_snowblossom_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SNOWY = createKey("flower_snowy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_TROPICS = createKey("flower_tropics");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_VIOLET = createKey("flower_violet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WASTELAND = createKey("flower_wasteland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WETLAND = createKey("flower_wetland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WILDFLOWER = createKey("flower_wildflower");
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        
        register(context, CVegetationFeatures.COLD_DESERT_ROCKS, Feature.FOREST_ROCK, new BlockStateConfiguration(Blocks.COBBLESTONE.defaultBlockState()));
        register(context, CVegetationFeatures.HIGH_GRASS, CBaseFeatures.HIGH_GRASS, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.BIG_DRIPLEAF, CBaseFeatures.BIG_DRIPLEAF, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.BIG_PUMPKIN, CBaseFeatures.BIG_PUMPKIN, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.BRAMBLE, CBaseFeatures.BRAMBLE, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.FALLEN_LOG, CBaseFeatures.FALLEN_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.FALLEN_FIR_LOG, CBaseFeatures.FALLEN_FIR_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.GOLDENROD, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.HUGE_CLOVER, CBaseFeatures.HUGE_CLOVER, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.HUGE_TOADSTOOL, CBaseFeatures.HUGE_TOADSTOOL, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.DRIPSTONE_SPLATTER, CBaseFeatures.DRIPSTONE_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.MOSS_SPLATTER, CBaseFeatures.MOSS_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.MYCELIUM_SPLATTER, CBaseFeatures.MYCELIUM_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.PATCH_BARLEY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_BLUE_HYDRANGEA, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_BUSH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_CATTAIL, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_DEAD_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_DESERT_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_DUNE_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_FERN, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
        register(context, CVegetationFeatures.PATCH_ICY_IRIS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_LILAC, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILAC))));
        register(context, CVegetationFeatures.PATCH_PEONY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.PEONY))));
        register(context, CVegetationFeatures.PATCH_REED, Feature.RANDOM_PATCH, waterPatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_SEA_OATS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_SPROUTS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_TALL_LAVENDER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_TUNDRA_SHRUBS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_WATERGRASS, Feature.RANDOM_PATCH, waterPatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.PATCH_WATERLILY_FLOWER, Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get())))));
        register(context, CVegetationFeatures.PUMPKIN_PATCH, CBaseFeatures.PUMPKIN_PATCH, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.RAINFOREST_CLIFFS_VINES, CBaseFeatures.RAINFOREST_CLIFFS_VINES, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.ROOTED_STUMP, CBaseFeatures.ROOTED_STUMP, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.ROSE_BUSH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH))));
        register(context, CVegetationFeatures.SCATTERED_ROCKS, CBaseFeatures.SCATTERED_ROCKS, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SCRUB, CBaseFeatures.SCRUB, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SUNFLOWER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SUNFLOWER))));
        register(context, CVegetationFeatures.SHORT_BAMBOO, CBaseFeatures.SHORT_BAMBOO, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SMALL_DRIPLEAF, CBaseFeatures.SMALL_DRIPLEAF, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SMALL_BROWN_MUSHROOM, CBaseFeatures.SMALL_BROWN_MUSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SMALL_RED_MUSHROOM, CBaseFeatures.SMALL_RED_MUSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.SMALL_TOADSTOOL, CBaseFeatures.SMALL_TOADSTOOL, NoneFeatureConfiguration.INSTANCE);
        register(context, CVegetationFeatures.TOADSTOOL_NORMAL, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.WASTELAND_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 2).build()))));

        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();

        for(int i = 1; i <= 4; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder.add(CBlocks.URANIUM_ORE.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }

        register(context, PATCH_CLOVER, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder)))));

        SimpleWeightedRandomList.Builder<BlockState> builder2 = SimpleWeightedRandomList.builder();

        for(int i = 1; i <= 4; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder2.add(CBlocks.URANIUM_ORE.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }

        register(context, FLOWER_SNOWBLOSSOM_GROVE, Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder2)))));

        register(context, CVegetationFeatures.FLOWER_CLOVER_PATCH, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.AZURE_BLUET))));
        register(context, CVegetationFeatures.FLOWER_CONIFEROUS_FOREST, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.CORNFLOWER.defaultBlockState(), 1).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_DEFAULT_EXTENDED, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_FIELD_1, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_FIELD_2, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.PINK_TULIP.defaultBlockState(), 1).add(Blocks.RED_TULIP.defaultBlockState(), 1).add(Blocks.WHITE_TULIP.defaultBlockState(), 1).add(Blocks.ORANGE_TULIP.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_GRASSLAND, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_LAVENDER, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.FLOWER_MOOR, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ALLIUM.defaultBlockState(), 1).add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_MYSTIC_GROVE, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.ALLIUM.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_OMINOUS_WOODS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.WITHER_ROSE))));
        register(context, CVegetationFeatures.FLOWER_ORIGIN_VALLEY, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_POPPY, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.POPPY))));
        register(context, CVegetationFeatures.FLOWER_RAINFOREST, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_SHRUBLAND, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ALLIUM))));
        register(context, CVegetationFeatures.FLOWER_SNOWY, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_TROPICS, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(CBlocks.URANIUM_ORE.get().defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_VIOLET, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.FLOWER_WASTELAND, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
        register(context, CVegetationFeatures.FLOWER_WETLAND, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BLUE_ORCHID.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, CVegetationFeatures.FLOWER_WILDFLOWER, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(CBlocks.URANIUM_ORE.get()))));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration waterPatchConfiguration(F feature, FC configuration, int tries)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.filtered(feature, configuration, BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration waterPatchConfiguration(F feature, FC configuration)
    {
        return waterPatchConfiguration(feature, configuration, 96);
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
