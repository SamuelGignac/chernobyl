//package com.example.chernobyl.blocks;
//
//import com.example.chernobyl.Chernobyl;
//import net.minecraft.util.valueproviders.UniformInt;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.DropExperienceBlock;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.util.function.Supplier;
//
//public class UraniumOre {
//
//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Chernobyl.MODID);
//
//    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore",
//            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(3,6))); // todo : custom properties
//
//    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
//        RegistryObject<T> toReturn = Chernobyl.BLOCKS.register(name, block);
//        registerBlockItem(name, toReturn);
//        return toReturn;
//    }
//
//    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
//        return Chernobyl.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
//    }
//
//    public static void register(IEventBus eventBus) {
//        BLOCKS.register(eventBus);
//    }
//
//}
