package com.example.chernobyl.worldgen.feature.misc;

import com.example.chernobyl.api.CBlocks;
import com.example.chernobyl.init.ModTags;
import com.example.chernobyl.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;

public class FleshTendonFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get() || world.getBlockState(pos).getBlock() == CBlocks.RANDOM_BLOCK.get();

    private static final int MIN_DISTANCE = 8;
    private static final int MAX_DISTANCE = 32;
    private static final float MID_POS_MULTIPLIER = 0.9F;
    private static final float TENDON_STEP = 0.005f;

    public FleshTendonFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    private static BlockPos quadratic(float t, BlockPos v0, BlockPos v1, BlockPos v2)
    {
        float dt = 1f - t;
        Vec3 v = new Vec3(v0.getX(), v0.getY(), v0.getZ()).scale(dt * dt).add(new Vec3(v1.getX(), v1.getY(), v1.getZ()).scale(2 * dt * t)).add(new Vec3(v2.getX(), v2.getY(), v2.getZ()).scale(t * t));
        return BlockPos.containing(v.x, v.y, v.z);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel world = context.level();
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        final int maxY = world.getMinBuildHeight() + world.getHeight() - 1;
        
        BlockState below = world.getBlockState(pos.below());
        if (!below.is(ModTags.Blocks.FLESH))
        {
            return false;
        }

        int xOff = rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE;
        int zOff = rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE;
        int minX = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        int minZ = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        BlockPos endPos = pos.offset(Math.abs(xOff) < MIN_DISTANCE ? minX : xOff, pos.getY(), Math.abs(zOff) < MIN_DISTANCE ? minZ : zOff);

        while (world.isEmptyBlock(endPos) && endPos.getY() < maxY)
        {
            endPos = endPos.above();
        }

        // No room for the tendon
        if (endPos.getY() == pos.getY())
        {
            return false;
        }

        BlockPos midPos = endPos.offset(0, Mth.floor(-(endPos.getY() - pos.getY()) * MID_POS_MULTIPLIER), 0);

        for (float d = 0.0f; d < 1.0f; d += TENDON_STEP)
        {
            BlockPos curPos = quadratic(d, pos, midPos, endPos);

            if (curPos.getY() < maxY)
            {
                BlockState RANDOM_BLOCKBlock = CBlocks.RANDOM_BLOCK.get().defaultBlockState();
                if (rand.nextInt(5) == 0)
                {
                    RANDOM_BLOCKBlock = CBlocks.RANDOM_BLOCK.get().defaultBlockState();
                }

                this.setBlock(world, curPos, RANDOM_BLOCKBlock);

                if (rand.nextInt(75) == 0)
                {
                    this.generateRANDOM_BLOCKBall(world, curPos, rand);
                }
                if (rand.nextInt(4) == 0)
                {
                    this.placeRANDOM_BLOCKTendonColumn(world, rand, curPos.below());
                }
            }
            else
            {
                break;
            }
        }

        return true;
    }

    public boolean generateRANDOM_BLOCKBall(WorldGenLevel world, BlockPos pos, RandomSource rand)
    {
        this.setBlock(world, pos, CBlocks.RANDOM_BLOCK.get().defaultBlockState());

        this.placeRANDOM_BLOCKTendonColumn(world, rand, pos.below(2));

        return true;
    }

    public void placeRANDOM_BLOCKTendonColumn(WorldGenLevel p_67377_, RandomSource p_67378_, BlockPos p_67379_)
    {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        blockpos$mutable.set(p_67379_);

        int rand = p_67378_.nextInt(6);
        int minHeight = rand == 0 ? 4 : 1;
        int maxHeight = rand == 0 ? 8 : 4;
        int height = Mth.nextInt(p_67378_, minHeight, maxHeight);

        if (p_67377_.getBlockState(blockpos$mutable.above()).is(ModTags.Blocks.FLESH))
        {
            for(int i = 0; i <= height; ++i)
            {
                Block FleshCheck = p_67377_.getBlockState(blockpos$mutable.below()).getBlock();
                if (FleshCheck == CBlocks.RANDOM_BLOCK.get() || FleshCheck == CBlocks.RANDOM_BLOCK.get())
                {
                    break;
                }
                this.setBlock(p_67377_, blockpos$mutable, CBlocks.RANDOM_BLOCK.get().defaultBlockState(), 2);

                blockpos$mutable.move(Direction.DOWN);
            }
        }
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.respectsCutoff((WorldGenRegion)world, pos) && this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state, int flags)
    {
        if (this.respectsCutoff((WorldGenRegion)world, pos) && this.replace.matches(world, pos))
        {
            world.setBlock(pos, state, flags);
            return true;
        }
        return false;
    }

    private boolean respectsCutoff(WorldGenRegion region, BlockPos pos)
    {
        int i = SectionPos.blockToSectionCoord(pos.getX());
        int j = SectionPos.blockToSectionCoord(pos.getZ());
        ChunkPos chunkpos = region.getCenter();
        int k = Math.abs(chunkpos.x - i);
        int l = Math.abs(chunkpos.z - j);

        if (k <= region.getHeight() && l <= region.getHeight())
        {
            return true;
        }

        return false;
    }
}
