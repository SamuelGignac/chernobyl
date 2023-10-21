
package com.example.chernobyl.init;

import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.api.block.CBlocks;
import com.example.chernobyl.api.item.CItems;
import com.example.chernobyl.items.Plutonium;
import com.example.chernobyl.items.Uranium;
import com.google.common.collect.ImmutableList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.List;

import static com.example.chernobyl.api.block.CBlocks.*;
import static com.example.chernobyl.api.item.CItems.*;

public class ModCreativeTab
{
    private static final List<RegistryObject<Item>> ITEM_BLACKLIST = ImmutableList.of(CItems.C_ICON);

    private static final List<RegistryObject<Block>> BLOCK_BLACKLIST = ImmutableList.of(RANDOM_BLOCK);

    public static void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
            event.accept(RANDOM_BLOCK.get());
        } else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(URANIUM.get());
            event.accept(PLUTONIUM.get());
        } else if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.accept(URANIUM_ORE.get());
            event.accept(PLUTONIUM_ORE.get());
        }
    }

//    public static void setup()
//    {
//        Chernobyl.CREATIVE_TAB_REGISTER.register("main", () ->
//            CreativeModeTab.builder()
//                .icon(() -> new ItemStack(CItems.C_ICON.get()))
//                .title(Component.translatable("item.chernobyl.c_icon"))
//                .displayItems((displayParameters, output) -> {
//                    // Add blocks
//                    for (Field field : CBlocks.class.getFields())
//                    {
//                        if (field.getType() != RegistryObject.class) continue;
//
//                        try
//                        {
//                            RegistryObject<Block> block = (RegistryObject)field.get(null);
//                            if (!BLOCK_BLACKLIST.contains(block)) {
//                                Chernobyl.LOGGER.info("Adding block: " + block.get().getName());
//                                output.accept(new ItemStack(block.get()));
//                            }
//                        }
//                        catch (IllegalAccessException e) {}
//                    }
//
//                    // Add items
//                    for (Field field : CItems.class.getFields())
//                    {
//                        if (field.getType() != RegistryObject.class) continue;
//
//                        try
//                        {
//                            RegistryObject<Item> item = (RegistryObject)field.get(null);
//                            if (!ITEM_BLACKLIST.contains(item))
//                                output.accept(new ItemStack(item.get()));
//                        }
//                        catch (IllegalAccessException e) {}
//                    }
//                }).build()
//        );
//    }
}
