package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.block.CBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SmallFumaroleFeature extends Feature<NoneFeatureConfiguration>
{
    public SmallFumaroleFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        int i = 0;

        for(int j = 0; j < 64; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if ((TreeFeature.isAirOrLeaves(world, blockpos) || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get()) && world.getBlockState(blockpos.below()).getBlock() == CBlocks.RANDOM_BLOCK.get())
            {
                if (rand.nextInt(5) == 0)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        world.setBlock(blockpos, CBlocks.RANDOM_BLOCK.get().defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), CBlocks.RANDOM_BLOCK.get().defaultBlockState(), 2);
                    }
                    else
                    {
                        world.setBlock(blockpos, CBlocks.RANDOM_BLOCK.get().defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);
                    }
                }

                ++i;
            }
        }

        return i > 0;
    }
}