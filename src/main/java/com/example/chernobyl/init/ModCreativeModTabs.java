package com.example.chernobyl.init;

import com.example.chernobyl.Chernobyl;
import com.example.chernobyl.api.CBlocks;
import com.example.chernobyl.api.CItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Chernobyl.MODID);

    public static final RegistryObject<CreativeModeTab> CHERNOBYL_TAB = CREATIVE_MODE_TABS.register("chernobyl_tab", () -> CreativeModeTab.builder().icon(
            () -> new ItemStack(CItems.URANIUM.get()))
            .title(Component.translatable("creativetab.chernobyl_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(CBlocks.NUKE_PARTICLE.get());
                pOutput.accept(CBlocks.URANIUM_ORE.get());
                pOutput.accept(CBlocks.PLUTONIUM_ORE.get());
                pOutput.accept(CItems.URANIUM.get());
                pOutput.accept(CItems.PLUTONIUM.get());
                pOutput.accept(CBlocks.BOMB_FRAME.get());
                pOutput.accept(CBlocks.NUCLEAR_BOMB.get());
                pOutput.accept(CItems.GEIGER_COUNTER.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
