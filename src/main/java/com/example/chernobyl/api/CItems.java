
package com.example.chernobyl.api;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class CItems
{
    public static RegistryObject<Item> C_ICON;

    public static RegistryObject<Item> PLUTONIUM;
    public static RegistryObject<Item> URANIUM;

    public static List<RegistryObject<Item>> getRegisteredItem() {
        return List.of(
                C_ICON,
                PLUTONIUM,
                URANIUM
        );
    }
}
