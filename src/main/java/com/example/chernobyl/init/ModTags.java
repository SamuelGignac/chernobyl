
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags
{
//    TODO: Trouver l'utilité de cette classe
    public static void setup()
    {
        Blocks.setup();
        Fluids.setup();
    }

    public static class Blocks
    {
        private static void setup() {}

        public static final TagKey<Block> FLESH = BlockTags.create(new ResourceLocation(Chernobyl.MODID, "flesh"));
    }

    public static class Fluids
    {
        private static void setup() {}
    }
}
