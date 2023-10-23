
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModParticles
{
//    public static final RegistryObject<SimpleParticleType> FALLOUT = register("fallout",
//    () -> new FalloutParticle(Minecraft.getInstance().level, 100, 100, 100, 1,1,1));

    public static void setup() {}

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return Chernobyl.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
