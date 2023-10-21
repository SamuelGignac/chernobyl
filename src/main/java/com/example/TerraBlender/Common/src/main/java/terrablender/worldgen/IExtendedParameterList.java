/**
 * Copyright (C) Glitchfiend
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.example.TerraBlender.Common.src.main.java.terrablender.worldgen;

import com.example.TerraBlender.Common.src.main.java.terrablender.api.RegionType;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Climate;

public interface IExtendedParameterList<T>
{
    void initializeForTerraBlender(RegistryAccess registryAccess, RegionType regionType, long seed);
    T findValuePositional(Climate.TargetPoint target, int x, int y, int z);
    int getUniqueness(int x, int y, int z);
//    Climate.RTree getTree(int uniqueness);
    int getTreeCount();
    boolean isInitialized();
}