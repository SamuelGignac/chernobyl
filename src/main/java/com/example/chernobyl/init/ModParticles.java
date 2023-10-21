
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModParticles
{
    public static final RegistryObject<SimpleParticleType> DRIPPING_BLOOD = register("dripping_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FALLING_BLOOD = register("falling_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LANDING_BLOOD = register("landing_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PUS = register("pus", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOWWORM = register("glowworm", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> JACARANDA_LEAVES = register("jacaranda_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SNOWBLOSSOM_LEAVES = register("snowblossom_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> YELLOW_AUTUMN_LEAVES = register("yellow_autumn_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ORANGE_AUTUMN_LEAVES = register("orange_autumn_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MAPLE_LEAVES = register("maple_leaves", () -> new SimpleParticleType(false));

    public static void setup() {}

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return Chernobyl.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
