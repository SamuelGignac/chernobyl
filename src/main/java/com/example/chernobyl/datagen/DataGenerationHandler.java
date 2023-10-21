
package com.example.chernobyl.datagen;

import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.init.ModBiomes;
import com.example.chernobyl.util.worldgen.CFeatureUtils;
import com.example.chernobyl.util.worldgen.CPlacementUtils;
import com.example.chernobyl.worldgen.carver.CConfiguredCarvers;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Chernobyl.MODID)
public class DataGenerationHandler
{
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_CARVER, CConfiguredCarvers::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, CFeatureUtils::bootstrap)
            .add(Registries.PLACED_FEATURE, CPlacementUtils::bootstrap)
            .add(Registries.BIOME, ModBiomes::bootstrapBiomes)
            .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), BUILDER, Set.of(Chernobyl.MODID)));
    }
}
