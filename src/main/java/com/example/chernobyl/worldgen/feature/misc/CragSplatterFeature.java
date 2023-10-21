package com.example.chernobyl.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CragSplatterFeature extends Feature<NoneFeatureConfiguration>
{
    public CragSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        BlockPos.MutableBlockPos mutable = pos.mutable();
        int placed = 0;
        int radius = rand.nextInt(8 - 2) + 2;

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
        {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
            {
                int dx = x - pos.getX();
                int dz = z - pos.getZ();
                if (dx * dx + dz * dz <= radius * radius)
                {
                    for (int k1 = pos.getY() - 6; k1 <= pos.getY() + 6; ++k1)
                    {
                        mutable.set(x, k1, z);
                        BlockState blockstate = worldIn.getBlockState(mutable);
                        BlockState blockstate1 = worldIn.getBlockState(mutable.move(Direction.UP));
                        mutable.move(Direction.DOWN);

                        if ((blockstate.getBlock() == Blocks.STONE || blockstate.getBlock() == Blocks.GRAVEL || blockstate.getBlock() == Blocks.ANDESITE || blockstate.getBlock() == Blocks.DIORITE || blockstate.getBlock() == Blocks.GRANITE || blockstate.getBlock() == Blocks.DIRT))
                        {
                            switch (rand.nextInt(14))
                            {
                                default:
                                case 0:
                                case 1:
                                    worldIn.setBlock(mutable, Blocks.COBBLESTONE.defaultBlockState(), 2);
                                    break;

                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                    worldIn.setBlock(mutable, Blocks.GRAVEL.defaultBlockState(), 2);
                                    break;

                                case 7:
                                case 8:
                                    worldIn.setBlock(mutable, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
                                    break;

                                case 9:
                                case 10:
                                    worldIn.setBlock(mutable, Blocks.ANDESITE.defaultBlockState(), 2);
                                    break;

                                case 11:
                                case 12:
                                case 13:
                                    if (worldIn.isStateAtPosition(mutable.move(Direction.UP), BlockBehaviour.BlockStateBase::isAir))
                                    {
                                        worldIn.setBlock(mutable, Blocks.GRASS.defaultBlockState(), 2);
                                        worldIn.setBlock(mutable.move(Direction.DOWN), Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                                    }
                                    break;
                            }

                            ++placed;
                            break;
                        }
                    }
                }
            }
        }

        return placed > 0;
    }
}
