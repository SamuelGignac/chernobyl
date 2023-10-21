package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.CBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SmallCrystalFeature extends Feature<NoneFeatureConfiguration>
{
    public SmallCrystalFeature(Codec<NoneFeatureConfiguration> deserializer)
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

        for(int j = 0; j < 128; ++j)
        {
            Direction direction = Direction.getRandom(rand);

            BlockState cluster_state;
            cluster_state = CBlocks.RANDOM_BLOCK.get().defaultBlockState();

            BlockState state = cluster_state.setValue(AmethystClusterBlock.FACING, direction);
            BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
            Block ground = world.getBlockState(blockpos.relative(direction.getOpposite())).getBlock();

            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos) && ground != Blocks.BEDROCK)
            {
                world.setBlock(blockpos, state, 2);

                ++i;
            }
        }

        return i > 10;
    }
}
