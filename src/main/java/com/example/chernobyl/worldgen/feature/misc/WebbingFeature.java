package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.block.CBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WebbingFeature extends Feature<NoneFeatureConfiguration>
{
    public WebbingFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel worldIn = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        int i = 0;
        int j = rand.nextInt(8 - 2) + 2;

        for (int k = pos.getX() - j; k <= pos.getX() + j; ++k)
        {
            for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l)
            {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= j * j)
                {
                    for (int k1 = pos.getY() - j; k1 <= pos.getY() + j; ++k1)
                    {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = worldIn.getBlockState(blockpos);
                        BlockState webbingstate = CBlocks.RANDOM_BLOCK.get().defaultBlockState();

                        int faces = 0;

                        for (Direction direction : Direction.values())
                        {
                            BlockState blockstate1 = worldIn.getBlockState(blockpos.relative(direction));
                            if (blockstate1 == Blocks.STONE.defaultBlockState() || blockstate1 == Blocks.ANDESITE.defaultBlockState() || blockstate1 == Blocks.DIORITE.defaultBlockState() || blockstate1 == Blocks.GRANITE.defaultBlockState() || blockstate1 == Blocks.DRIPSTONE_BLOCK.defaultBlockState() || blockstate1 == Blocks.CALCITE.defaultBlockState() || blockstate1 == Blocks.TUFF.defaultBlockState() || blockstate1 == Blocks.DEEPSLATE.defaultBlockState())
                            {
                                webbingstate = webbingstate.setValue(MultifaceBlock.getFaceProperty(direction), true);
                                faces++;
                            }
                        }

                        if (blockstate.isAir() && faces > 0)
                        {
                            worldIn.setBlock(blockpos, webbingstate, 2);

                            ++i;
                            break;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
