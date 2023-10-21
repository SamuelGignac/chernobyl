package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.block.CBlocks;
import com.example.chernobyl.blocks.BrambleBlock;
import com.example.chernobyl.util.SimpleBlockPredicate;
import com.example.chernobyl.util.biome.GeneratorUtil;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BrambleFeature extends Feature<NoneFeatureConfiguration>
{
    public BrambleFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    protected SimpleBlockPredicate placeOn = (world, pos) ->
    {
        BlockState state = world.getBlockState(pos);
        return world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING) || state.getBlock() == Blocks.NETHERRACK;
    };

    protected SimpleBlockPredicate replace = (world, pos) -> world.getBlockState(pos).isAir();

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        for (int i = 0; i < 128; ++i)
        {
            BlockPos genPos = startPos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));

            if (this.placeOn.matches(world, genPos.below()) && this.replace.matches(world, genPos))
            {
                int targetLength = GeneratorUtil.nextIntBetween(rand, 15, 30);
                int height = 0;
                int direction = rand.nextInt(4) + 2;

                for (int length = 0; length <= targetLength && replace.matches(world, genPos); length++)
                {
                    //if (BrambleBlock.canPlaceBlockAt(world, genPos))
                    //{
                    world.setBlock(genPos, ((BrambleBlock) CBlocks.RANDOM_BLOCK.get()).makeConnections(world, genPos), 2);

                    for (Direction face : Direction.values())
                    {
                        if (world.getBlockState(genPos.relative(face)).getBlock() == CBlocks.RANDOM_BLOCK.get())
                        {
                            world.setBlock(genPos.relative(face), ((BrambleBlock)CBlocks.RANDOM_BLOCK.get()).makeConnections(world, genPos.relative(face)), 2);
                        }
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        direction = rand.nextInt(4) + 2;
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        int leafDirection = rand.nextInt(6);
                        BlockPos leafPos = genPos.relative(Direction.values()[leafDirection]);
                        if (world.isEmptyBlock(leafPos))
                        {
                            world.setBlock(leafPos, CBlocks.RANDOM_BLOCK.get().defaultBlockState().setValue(DirectionalBlock.FACING, Direction.values()[leafDirection]), 19);
                            for (Direction face : Direction.values())
                            {
                                if (world.getBlockState(leafPos.relative(face)).getBlock() == CBlocks.RANDOM_BLOCK.get())
                                {
                                    world.setBlock(leafPos.relative(face), ((BrambleBlock)CBlocks.RANDOM_BLOCK.get()).makeConnections(world, leafPos.relative(face)), 2);
                                }
                            }
                        }
                    }

                    switch (rand.nextInt(6))
                    {
                        case 0: case 1:
                        if (height <= 8)
                        {
                            genPos = genPos.above();
                            height++;
                        }
                        break;
                        case 2:
                            if (height >= 0)
                            {
                                genPos = genPos.below();
                                height--;
                            }
                            break;
                        default:
                            genPos = genPos.relative(Direction.values()[direction]);
                            break;
                    }
                }
            }
        }
        return true;
    }
}