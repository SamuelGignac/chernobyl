
package com.example.chernobyl.init;

import com.example.chernobyl.api.CBlocks;
import com.example.chernobyl.api.CItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.example.chernobyl.api.CBlocks.RANDOM_BLOCK;
import static com.example.chernobyl.api.CItems.C_ICON;

public class ModCreativeTab
{
    private static final List<RegistryObject<Item>> ITEM_BLACKLIST = ImmutableList.of(C_ICON);

    private static final List<RegistryObject<Block>> BLOCK_BLACKLIST = ImmutableList.of(RANDOM_BLOCK);

    public static void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS))
            addNaturalBlock(event);
        else if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS))
            addIngredients(event);

    }

    private static void addIngredients(BuildCreativeModeTabContentsEvent event){
        for (RegistryObject<Item> items : CItems.getRegisteredItem()) {
            if (!ITEM_BLACKLIST.contains(items))
                event.accept(items.get());
        }
    }

    private static void addNaturalBlock(BuildCreativeModeTabContentsEvent event){
        for (RegistryObject<Block> blocks : CBlocks.getRegisteredBlock()) {
            if (!BLOCK_BLACKLIST.contains(blocks))
                event.accept(blocks.get());
        }
    }
}
