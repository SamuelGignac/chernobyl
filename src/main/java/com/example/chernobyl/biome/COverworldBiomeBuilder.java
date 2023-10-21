package com.example.chernobyl.biome;

import com.example.chernobyl.api.biome.CBiomes;
import com.example.chernobyl.init.ModConfig;
import com.example.chernobyl.util.biome.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class COverworldBiomeBuilder
{
    private static final float VALLEY_SIZE = 0.05F;
    private static final float LOW_START = 0.26666668F;
    public static final float HIGH_START = 0.4F;
    private static final float HIGH_END = 0.93333334F;
    private static final float PEAK_SIZE = 0.1F;
    public static final float PEAK_START = 0.56666666F;
    private static final float PEAK_END = 0.7666667F;
    public static final float NEAR_INLAND_START = -0.11F;
    public static final float MID_INLAND_START = 0.03F;
    public static final float FAR_INLAND_START = 0.3F;
    public static final float EROSION_INDEX_1_START = -0.78F;
    public static final float EROSION_INDEX_2_START = -0.375F;

    protected final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    /* Terminology:
        Continentalness: Low to generate near coasts, far to generate away from coasts
        Erosion: Low is hilly terrain, high is flat terrain
     */

    protected final Climate.Parameter[] temperatures = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-0.45F, -0.15F),
            Climate.Parameter.span(-0.15F, 0.2F),
            Climate.Parameter.span(0.2F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };

    protected final Climate.Parameter[] humidities = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.35F),
            Climate.Parameter.span(-0.35F, -0.1F),
            Climate.Parameter.span(-0.1F, 0.1F),
            Climate.Parameter.span(0.1F, 0.3F),
            Climate.Parameter.span(0.3F, 1.0F)
    };

    protected final Climate.Parameter[] erosions = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.78F),
            Climate.Parameter.span(-0.78F, -0.375F),
            Climate.Parameter.span(-0.375F, -0.2225F),
            Climate.Parameter.span(-0.2225F, 0.05F),
            Climate.Parameter.span(0.05F, 0.45F),
            Climate.Parameter.span(0.45F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };

    protected static final Climate.Parameter COMMON_RARENESS_RANGE = Climate.Parameter.span(-1.0F, 0.35F);
    protected static final Climate.Parameter RARE_RARENESS_RANGE = Climate.Parameter.span(0.35F, 1.0F);

    protected final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
    protected final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
    protected final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    protected final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    protected final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    protected final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    protected final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    protected final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    protected final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    protected final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN,      Biomes.COLD_OCEAN,      Biomes.OCEAN,      Biomes.LUKEWARM_OCEAN,      Biomes.WARM_OCEAN}
    };

//    TODO: Changer pour le meilleur
    private final ResourceKey<Biome>[][] RADIOACTIVE_BIOMES = new ResourceKey[][]{
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {null,                            null,                            null,                   null,                    null},
            {null,                            null,                            null,                   null,                    null}
    };

    public void addBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addOffCoastBiomes(biomeRegistry, mapper);
        this.addInlandBiomes(biomeRegistry, mapper);
        this.addUndergroundBiomes(biomeRegistry, mapper);
    }

    private void addOffCoastBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> islandBiomeC = this.pickIslandBiomeC(biomeRegistry, i, j);

                this.addSurfaceBiome(mapper, temperature, humidity, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, islandBiomeC);
            }

            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    private void addInlandBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        /*
            Weirdness ranges map to specific slices in a repeating triangle wave fashion.
                   PEAKS                           PEAKS
               HIGH     HIGH                   HIGH     HIGH
            MID             MID             MID             MID
                               LOW       LOW
                                  VALLEYS
         */

        // First cycle
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(biomeRegistry, mapper, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(biomeRegistry, mapper, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(biomeRegistry, mapper, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(0.26666668F, 0.4F));

        // Second cycle is truncated
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(biomeRegistry, mapper, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    protected void addPeaks(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeC                         = this.pickMiddleBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeC          = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeC                        = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome                      = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeC                   = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome                         = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiomeC                           = this.pickPeakBiomeC(biomeRegistry,  i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeC);
            }
        }

    }

    protected void addHighSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeC                    = this.pickMiddleBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeC     = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeC            = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeC       = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> slopeBiomeC              = this.pickSlopeBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> peakBiomeC               = this.pickPeakBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[0], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeC);
            }
        }

    }

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
                ResourceKey<Biome> middleBiomeC                      = this.pickMiddleBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeC       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> extremeHillsBiomeC       = this.pickExtremeHillsBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> plateauBiomeC            = this.pickPlateauBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> beachBiome                 = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome        = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> slopeBiomeC              = this.pickSlopeBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeC              = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, slopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? slopeBiomeC : plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, plateauBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleBiomeC);

                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, beachBiome);
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeC);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeC);
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
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, middleBiomeC);
                }

                if (i == 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeC);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }

    }

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
                ResourceKey<Biome> middleBiomeC                      = this.pickMiddleBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeC       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdC(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> beachBiome                   = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome               = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome          = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> swampBiomeC                = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);

                // Lowest to low erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBadlandsOrSlopeBiomeC);

                // Reduced to moderate erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeC);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeC);

                // Moderate to increased erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0.0F, beachBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeC);

                // High erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, middleBiomeC);

                // Highest erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);

                if (i == 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeC);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }

    }

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
                ResourceKey<Biome> middleBiomeC               = this.pickMiddleBiomeC(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeC                = this.pickSwampBiomeC(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeC);

                if (i != 0)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeC);
                }
            }
        }
    }

//        TODO: Changer pour le meilleur (Surtout les null)
    protected void addUndergroundBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addBottomBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.erosions[0], this.erosions[1]), this.FULL_RANGE, 0.0F, Biomes.DEEP_DARK);
    }

    protected ResourceKey<Biome> pickIslandBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return null;
    }

    protected ResourceKey<Biome> pickMiddleBiomeVanilla(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {

        if (weirdness.max() < 0L)
        {
            return null;
        }
        else
        {
            return null;
        }
    }

    protected ResourceKey<Biome> pickMiddleBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, null, null);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, null, middleBiome);
        }
    }

    protected ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return temperatureIndex == 0 ? this.pickSlopeBiomeC(biomeRegistry, temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeC(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
    }

    protected ResourceKey<Biome> pickSwampBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, null, this.pickMiddleBiomeC(biomeRegistry, temperatureIndex, humidityIndex, weirdness), Biomes.SWAMP);
    }

    protected ResourceKey<Biome> maybePickShatteredBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> extremeHillsBiome)
    {
        return temperatureIndex > 1 && humidityIndex < 4 && weirdness.max() >= 0L ? BiomeUtil.biomeOrFallback(biomeRegistry, null, Biomes.WINDSWEPT_SAVANNA) : extremeHillsBiome;
    }

    protected ResourceKey<Biome> pickShatteredCoastBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = weirdness.max() >= 0L ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiome(biomeRegistry, temperatureIndex, humidityIndex);
        return this.maybePickShatteredBiome(biomeRegistry, temperatureIndex, humidityIndex, weirdness, resourcekey);
    }

    protected ResourceKey<Biome> pickBeachBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        if (temperatureIndex == 0)
            return Biomes.SNOWY_BEACH;
        else if (temperatureIndex == 2 && humidityIndex < 2)
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, null, Biomes.BEACH);
        }
        else
        {
            return temperatureIndex == 4 ? Biomes.DESERT : Biomes.BEACH;
        }
    }

    protected ResourceKey<Biome> pickBadlandsBiome(int humidityIndex, Climate.Parameter weirdness)
    {
        if (humidityIndex < 2)
        {
            return weirdness.max() < 0L ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS;
        }
        else
        {
            return humidityIndex < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
        }
    }

    protected ResourceKey<Biome> pickPlateauBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return BiomeUtil.biomeOrFallback(biomeRegistry, null, null);
        else return BiomeUtil.biomeOrFallback(biomeRegistry, null, null, null);
    }

    protected ResourceKey<Biome> pickPeakBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (temperatureIndex <= 2)
        {
            return weirdness.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
        }
        else
        {
            return temperatureIndex == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(humidityIndex, weirdness);
        }
    }

    protected ResourceKey<Biome> pickPeakBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> peakBiome = this.pickPeakBiome(temperatureIndex, humidityIndex, weirdness);

        if (temperatureIndex == 1) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, peakBiome);
        else if (temperatureIndex == 2) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, peakBiome);
        else if (temperatureIndex == 3 && humidityIndex >= 3) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, peakBiome);
        else return peakBiome;
    }

    protected ResourceKey<Biome> pickSlopeBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> plateauBiome = this.pickPlateauBiomeC(biomeRegistry, temperatureIndex, humidityIndex, weirdness);

        if (temperatureIndex == 1) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, plateauBiome);
        else if (temperatureIndex == 2) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, plateauBiome);
        else if (temperatureIndex == 3 && humidityIndex >= 3) return BiomeUtil.biomeOrFallback(biomeRegistry, CBiomes.RADIOACTIVE, plateauBiome);
        else return plateauBiome;
    }

    protected ResourceKey<Biome> pickExtremeHillsBiomeVanilla(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = null;
        return resourcekey == null ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : resourcekey;
    }

    protected ResourceKey<Biome> pickExtremeHillsBiomeC(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, null, this.pickExtremeHillsBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }

    protected void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
    }

    protected void addUndergroundBiome(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        if (!ModConfig.isBiomeEnabled(biome))
            return;

        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), biome));
    }

    private void addBottomBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.1F), weirdness, offset), biome));
    }
}