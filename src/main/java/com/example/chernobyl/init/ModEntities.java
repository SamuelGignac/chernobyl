
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModEntities
{
    public static void setup()
    {
        registerEntities();
    }

    public static void registerEntities()
    {
//        TODO: Register entities here
    }

    public static RegistryObject<EntityType<?>> registerEntity(Supplier<EntityType<?>> typeSupplier, String name)
    {
        return Chernobyl.ENTITY_TYPE_REGISTER.register(name, typeSupplier);
    }
}
