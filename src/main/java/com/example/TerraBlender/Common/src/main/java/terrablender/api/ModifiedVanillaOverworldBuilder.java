/**
 * Copyright (C) Glitchfiend
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.example.TerraBlender.Common.src.main.java.terrablender.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModifiedVanillaOverworldBuilder
{
    private Map<ResourceKey<Biome>, ResourceKey<Biome>> originalBiomeMappings = Maps.newHashMap();
    private Map<Climate.ParameterPoint, ResourceKey<Biome>> parameterBiomeMappings = Maps.newHashMap();
    private Map<Climate.ParameterPoint, Climate.ParameterPoint> parameterMappings = Maps.newHashMap();
    private List<Climate.ParameterPoint> parametersToRemove = Lists.newArrayList();
    private final OverworldBiomeBuilder biomeBuilder = new OverworldBiomeBuilder();

    public ModifiedVanillaOverworldBuilder() {}

    /**
     * Replaces a Vanilla biome with another.
     * @param original the biome to replace.
     * @param replacement the biome to replace the original biome.
     */
    public void replaceBiome(ResourceKey<Biome> original, ResourceKey<Biome> replacement)
    {
        originalBiomeMappings.put(original, replacement);
    }

    /**
     * Replaces a Vanilla biome at a given {@link Climate.ParameterPoint} with another.
     * @param point the parameter point corresponding to a Vanilla biome.
     * @param biome the biome to be used at the provided parameter point.
     */
    public void replaceBiome(Climate.ParameterPoint point, ResourceKey<Biome> biome)
    {
        parameterBiomeMappings.put(point, biome);
    }

    /**
     * Replaces a {@link Climate.ParameterPoint} with another.
     * @param original the {@link Climate.ParameterPoint} to be replaced.
     * @param replacement the {@link Climate.ParameterPoint} to replace the original.
     */
    public void replaceParameter(Climate.ParameterPoint original, Climate.ParameterPoint replacement)
    {
        parameterMappings.put(original, replacement);
    }

    /**
     * Removes a {@link Climate.ParameterPoint} parameter.
     * @param parameter the {@link Climate.ParameterPoint} to be removed.
     */
    public void removeParameter(Climate.ParameterPoint parameter)
    {
        parametersToRemove.add(parameter);
    }

    /**
     * Builds a list of {@link Climate.ParameterPoint} and {@link ResourceKey<Biome>} pairs.
     * @return the built list.
     */
    public List<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> build()
    {
        ImmutableList.Builder<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> builder = new ImmutableList.Builder<>();
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper = pair -> {
            Climate.ParameterPoint parameters = pair.getFirst();
            ResourceKey<Biome> biome = pair.getSecond();

            if (!parametersToRemove.contains(parameters))
            {
                // Replace the biome if required.
                if (originalBiomeMappings.containsKey(biome))
                    biome = originalBiomeMappings.get(biome);
                else if (parameterBiomeMappings.containsKey(parameters))
                    biome = parameterBiomeMappings.get(parameters);

                // Replace the original parameters if required
                if (parameterMappings.containsKey(parameters))
                    parameters = parameterMappings.get(parameters);

                builder.add(Pair.of(parameters, biome));
            }
        };

        // TODO: 2023-10-21 Build avec le biomeBuilder
//        biomeBuilder.addBiomes(mapper);
        return builder.build();
    }
}
