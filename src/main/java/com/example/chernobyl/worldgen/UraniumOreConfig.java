//package com.example.chernobyl.worldgen;
//
//import com.example.chernobyl.Chernobyl;
//import com.example.chernobyl.blocks.UraniumOre;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.worldgen.BootstapContext;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
//import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
//import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
//import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
//
//import java.util.List;
//
//public class UraniumOreConfig {
//
//    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_URANIUM_ORE_KEY = registerKey("uranium_ore");
//
//    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
//        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
//
//        List<OreConfiguration.TargetBlockState> overworldUraniumOres = List.of(
//                OreConfiguration.target(stoneReplaceable, UraniumOre.URANIUM_ORE.get().defaultBlockState()));
//
//        register(context, OVERWORLD_URANIUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldUraniumOres, 9));
//    }
//
//    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
//        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Chernobyl.MODID, name));
//    }
//
//    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
//        context.register(key, new ConfiguredFeature<>(feature, configuration));
//    }
//
//}
