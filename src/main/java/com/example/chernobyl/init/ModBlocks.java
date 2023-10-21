
package com.example.chernobyl.init;

import com.example.chernobyl.Chernobyl;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.example.chernobyl.api.CBlocks.*;
import static net.minecraft.world.level.block.Blocks.COBBLESTONE;
import static net.minecraft.world.level.block.Blocks.DIAMOND_ORE;

public class ModBlocks
{
    public static void setup()
    {
        registerBlocks();
    }

//    TODO: Change Block Properties
    public static void registerBlocks()
    {
//        Nos Blocks
        PLUTONIUM_ORE = registerBlock(
                () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DIAMOND_ORE), UniformInt.of(3,6)),
                "plutonium_ore");

        URANIUM_ORE = registerBlock(
                () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(DIAMOND_ORE), UniformInt.of(3,6)),
                "uranium_ore");

        RANDOM_BLOCK = registerBlock(
                () -> new Block(BlockBehaviour.Properties.copy(COBBLESTONE)),
                "random_block");

    }

    public static RegistryObject<Block> registerBlock(Supplier<Block> blockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = Chernobyl.BLOCK_REGISTER.register(name, blockSupplier);
        Chernobyl.ITEM_REGISTER.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
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

    @OnlyIn(Dist.CLIENT)
    public static void setRenderTypes()
    {
//        RenderType cutoutMippedRenderType = RenderType.cutoutMipped();
//        RenderType cutoutRenderType = RenderType.cutout();
//        ItemBlockRenderTypes.setRenderLayer(POTTED_GLOWSHROOM.get(), cutoutRenderType);
    }
}
