
package com.example.chernobyl.init;

import com.example.chernobyl.Chernobyl;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.example.chernobyl.api.item.CItems.*;

public class ModItems
{
    public static void setup()
    {
        registerItems();
    }

    private static void registerItems()
    {
        C_ICON = registerItem(() -> new Item(new Item.Properties()), "bop_icon");

        PLUTONIUM = registerItem(() -> new Item(new Item.Properties()), "plutonium");
        URANIUM = registerItem(() -> new Item(new Item.Properties()), "uranium");
    }

    public static RegistryObject<Item> registerItem(Supplier<Item> itemSupplier, String name)
    {
        return Chernobyl.ITEM_REGISTER.register(name, itemSupplier);
    }
}