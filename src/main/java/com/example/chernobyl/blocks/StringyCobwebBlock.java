package com.example.chernobyl.blocks;

import com.example.chernobyl.api.CBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class StringyCobwebBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    protected static final VoxelShape SHAPE_X = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape SHAPE_Z = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);

    public StringyCobwebBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CONNECTED, false));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONNECTED);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_)
    {
        switch((Direction)p_220053_1_.getValue(FACING))
        {
            case EAST:
            case WEST:
                return SHAPE_X;
            case NORTH:
            case SOUTH:
            default:
                return SHAPE_Z;
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        Direction direction = state.getValue(FACING);
        BlockPos abovePos = pos.relative(direction).above();
        BlockPos belowPos = pos.below();
        BlockState aboveState = level.getBlockState(abovePos);
        BlockState belowState = level.getBlockState(belowPos);

        BlockPos belowDirPos = pos.relative(direction.getOpposite()).below();
        BlockState belowDirState = level.getBlockState(belowDirPos);

        if ((aboveState.isSolid() || aboveState.getBlock() == CBlocks.RANDOM_BLOCK.get()) && (belowState.isSolid() || belowDirState.getBlock() == CBlocks.RANDOM_BLOCK.get()))
            return true;

        return false;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean p_55572_)
    {
        super.onRemove(state, level, pos, newState, p_55572_);

        Direction direction = state.getValue(FACING);
        BlockPos abovePos = pos.relative(direction).above();
        BlockPos belowPos = pos.relative(direction.getOpposite()).below();
        BlockState aboveState = level.getBlockState(abovePos);
        BlockState belowState = level.getBlockState(belowPos);

        if (aboveState.getBlock() == CBlocks.RANDOM_BLOCK.get())
            level.destroyBlock(abovePos, false);

        if (belowState.getBlock() == CBlocks.RANDOM_BLOCK.get())
            level.destroyBlock(belowPos, false);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockState blockstate = super.getStateForPlacement(context);
        Level LevelReader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for (Direction direction : adirection)
        {
            if (direction.getAxis().isHorizontal())
            {
                blockstate = blockstate.setValue(FACING, direction.getOpposite());
                if (blockstate.canSurvive(LevelReader, blockpos))
                {
                    return blockstate;
                }
            }
        }

        return null;
    }

}
