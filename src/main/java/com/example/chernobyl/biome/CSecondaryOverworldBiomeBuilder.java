
package com.example.chernobyl.biome;

import com.example.chernobyl.api.CBiomes;
import com.example.chernobyl.util.biome.BiomeUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public class CSecondaryOverworldBiomeBuilder extends COverworldBiomeBuilder
{
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_C = new ResourceKey[][]{
            {CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE},
            {CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE},
            {CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE},
            {CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE},
            {CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE, CBiomes.RADIOACTIVE}
    };

    @Override
    protected ResourceKey<Biome> pickMiddleBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    @Override
    protected ResourceKey<Biome> pickPlateauBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex]);
        else return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES_C[temperatureIndex][humidityIndex]);
    }
}
