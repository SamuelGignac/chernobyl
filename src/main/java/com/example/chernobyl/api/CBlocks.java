package com.example.chernobyl.api;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class CBlocks {
    public static RegistryObject<Block> PLUTONIUM_ORE;
    public static RegistryObject<Block> URANIUM_ORE;
    public static RegistryObject<Block> BOMB_FRAME;
    public static RegistryObject<Block> NUCLEAR_BOMB;
    public static RegistryObject<Block> NUKE_PARTICLE;
    public static RegistryObject<Block> RANDOM_BLOCK;

    public static List<RegistryObject<Block>> getRegisteredBlock() {
        return List.of(
                PLUTONIUM_ORE,
                URANIUM_ORE,
                RANDOM_BLOCK
        );
    }
}
