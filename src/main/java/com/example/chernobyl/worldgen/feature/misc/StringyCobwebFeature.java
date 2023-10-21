package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.block.CBlocks;
import com.example.chernobyl.blocks.StringyCobwebBlock;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StringyCobwebFeature extends Feature<NoneFeatureConfiguration>
{
    private static final int MIN_DISTANCE = 2;
    private static final int MAX_DISTANCE = 16;

    public StringyCobwebFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    private boolean moveDiagnonally(WorldGenLevel level, BlockPos origin, Direction direction, boolean place)
    {
        int distance;
        boolean connected = true;

        for (distance = 0; distance < MAX_DISTANCE; distance++)
        {
            if (distance > 0)
            {
                connected = false;
            }

            BlockPos pos = origin.relative(direction, distance).above(distance);
            BlockState state = level.getBlockState(pos);

            if (!state.isAir())
            {
                // Don't allow connecting to non-solid materials
                if (!state.isSolid()) return false;

                // Discontinue once we hit a block
                break;
            }

            if (place) this.setBlock(level, pos, CBlocks.RANDOM_BLOCK.get().defaultBlockState().setValue(StringyCobwebBlock.FACING, direction).setValue(StringyCobwebBlock.CONNECTED, connected));
        }

        if (distance < MIN_DISTANCE || distance >= MAX_DISTANCE) return false;
        return true;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel level = context.level();
        RandomSource rand = context.random();
        BlockPos origin = context.origin();
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

        if (!this.moveDiagnonally(level, origin, dir, false))
            return false;

        return this.moveDiagnonally(level, origin, dir, true);
    }
}
