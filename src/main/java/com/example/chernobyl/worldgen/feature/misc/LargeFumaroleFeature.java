package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.CBlocks;
import com.example.chernobyl.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LargeFumaroleFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get();
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get();

    public LargeFumaroleFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        if (!world.isEmptyBlock(pos))
        {
            return false;
        }
        else
        {
            if (!this.placeOn.matches(world, pos.below()))
            {
                return false;
            }
            else
            {
                int height = -1 + rand.nextInt(3);
                int radius = 1;

                for (int x = -radius; x <= radius; x++)
                {
                    for (int z = -radius; z <= radius; z++)
                    {
                        if (height > -1)
                        {
                            for (int y = 0; y <= height; y++)
                            {
                                this.setBlock(world, pos.offset(x, y, z), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                            }
                        }

                        if ((x == -radius || x == radius) && (z == -radius || z == radius))
                        {
                            if (height > -1)
                            {
                                this.setBlock(world, pos.offset(x, height+1, z), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                            }
                        }
                        else
                        {
                            this.setBlock(world, pos.offset(x, -1, z), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                            this.setBlock(world, pos.offset(x, height+1, z), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                            this.setBlock(world, pos.offset(x, height+2, z), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                        }
                    }
                }

                this.setBlock(world, pos.offset(0, height+2, 0), CBlocks.RANDOM_BLOCK.get().defaultBlockState());
                this.setBlock(world, pos.above(height+3), CBlocks.RANDOM_BLOCK.get().defaultBlockState());

                return true;
            }
        }
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos)
    {
        for (int y = 0; y <= 4; y++)
        {
            for (int x = -1; x <= 1; x++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    BlockPos pos1 = pos.offset(x,y,z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
