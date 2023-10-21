
package com.example.chernobyl.biome;

import com.example.chernobyl.api.CBiomes;
import com.example.chernobyl.util.biome.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class CRareOverworldBiomeBuilder extends COverworldBiomeBuilder
{
    private final ResourceKey<Biome>[][] RARE_BIOMES_C = new ResourceKey[][]{
            {null, null, null,                    CBiomes.RADIOACTIVE, null},
            {null, null, CBiomes.RADIOACTIVE, null,                     null},
            {null, null, null,                    CBiomes.RADIOACTIVE,   null},
            {null, null, null,                    null,                     CBiomes.RADIOACTIVE},
            {null, null, null,                    null,                     null}
    };

    @Override
    protected void addPeaks(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> rareBiomeC                           = this.pickRareBiomeC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeC                        = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome                      = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeC                   = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome                         = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiomeC                           = this.pickPeakBiomeC(biomeRegistry,  i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, rareBiomeC);
            }
        }
    }

    @Override
    protected void addHighSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> rareBiomeC                      = this.pickRareBiomeC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeC            = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome          = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeC       = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> slopeBiomeC              = this.pickSlopeBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> peakBiome                  = this.pickPeakBiome(i, j, weirdness);
                ResourceKey<Biome> peakBiomeC               = this.pickPeakBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[0], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[1], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, rareBiomeC);
            }
        }

    }

    @Override
    protected void addMidSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);

                ResourceKey<Biome> extremeHillsBiomeC       = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> plateauBiomeC            = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> beachBiome                 = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome        = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> slopeBiomeC              = this.pickSlopeBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeC              = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> rareBiomeC               = this.pickRareBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? slopeBiomeC : plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, rareBiomeC);

                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, beachBiome);
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, rareBiomeC);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, rareBiomeC);
                }

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeC);
                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, rareBiomeC);
                }

                if (i == 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, rareBiomeC));
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }

    }

    @Override
    protected void addLowSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);

                ResourceKey<Biome> beachBiome                   = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome               = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome          = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> rareBiomeC                 = this.pickRareBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeC                = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);

                // Lowest to low erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, rareBiomeC);

                // Reduced to moderate erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, rareBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, rareBiomeC);

                // Moderate to increased erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0.0F, beachBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, rareBiomeC);

                // High erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, rareBiomeC);

                // Highest erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);

                if (i == 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, rareBiomeC));
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }

    }

    @Override
    protected void addValleys(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, Biomes.RIVER);

        // Coastal watery valleys
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, Biomes.RIVER);

        // Inland watery valleys
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> rareBiomeC                 = this.pickRareBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeC                = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, rareBiomeC);

                if (i != 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }
    }

    private ResourceKey<Biome> pickRareBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = this.pickMiddleBiomeC(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.RARE_BIOMES_C[temperatureIndex][humidityIndex], middleBiome);
    }
}