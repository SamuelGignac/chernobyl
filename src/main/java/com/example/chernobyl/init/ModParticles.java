
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModParticles
{
    public static final RegistryObject<SimpleParticleType> RADIATION = register("radiation", () -> new SimpleParticleType(false));

    public static void setup() {}

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return Chernobyl.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
