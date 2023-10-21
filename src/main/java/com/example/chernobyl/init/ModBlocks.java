
package com.example.chernobyl.init;

import com.example.chernobyl.Chernobyl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static com.example.chernobyl.api.block.CBlocks.*;

public class ModBlocks
{
    public static void setup()
    {
        registerBlocks();
    }

    public static void registerBlocks()
    {
//        Nos Blocks
        PLUTONIUM_ORE = registerBlock(() -> new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.DIAMOND_ORE)), "plutonium_ore");
        URANIUM_ORE = registerBlock(() -> new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.DIAMOND_ORE)), "uranium_ore");
        RANDOM_BLOCK = registerBlock(() -> new Block(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.DIAMOND_ORE)), "random_block");
    }

    @OnlyIn(Dist.CLIENT)
    public static void setRenderTypes()
    {
//        RenderType cutoutMippedRenderType = RenderType.cutoutMipped();
//        RenderType cutoutRenderType = RenderType.cutout();
//        ItemBlockRenderTypes.setRenderLayer(POTTED_GLOWSHROOM.get(), cutoutRenderType);
    }

    private static RotatedPillarBlock log(MapColor MapColor, MapColor MapColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(SoundType.WOOD));
    }

    private static RotatedPillarBlock logNonIgniting(MapColor MapColor, MapColor MapColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(SoundType.WOOD));
    }

    public static RegistryObject<Block> registerBlock(Supplier<Block> blockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = Chernobyl.BLOCK_REGISTER.register(name, blockSupplier);
        Chernobyl.ITEM_REGISTER.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }

    public static RegistryObject<Block> registerBlockNoBlockItem(Supplier<Block> blockSupplier, String name)
    {
        return registerBlock(blockSupplier, null, name);
    }

    public static RegistryObject<Block> registerBlock(Supplier<Block> blockSupplier, Supplier<BlockItem> itemBlockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = Chernobyl.BLOCK_REGISTER.register(name, blockSupplier);

        if (itemBlockSupplier != null)
        {
            Chernobyl.ITEM_REGISTER.register(name, itemBlockSupplier);
        }

        return blockRegistryObject;
    }

    public static RegistryObject<BlockEntityType<?>> registerBlockEntityType(String name, BlockEntityType.BlockEntitySupplier<?> factoryIn, Supplier<List<Block>> validBlocks)
    {
        return Chernobyl.BLOCK_ENTITY_REGISTER.register(name, () -> BlockEntityType.Builder.of(factoryIn, validBlocks.get().toArray(new Block[0])).build(null));
    }

    private static Boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)true;
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)false;
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
    }
}
