package com.example.chernobyl.item.custom;

import com.example.chernobyl.sound.LoopSound;
import com.example.chernobyl.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionHand;

public class GeigerCounterItem extends Item {

    private LoopSound soundInstance;

    public GeigerCounterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            // If the sound is already playing, stop it
            if (soundInstance != null && Minecraft.getInstance().getSoundManager().isActive(soundInstance)) {
                Minecraft.getInstance().getSoundManager().stop(soundInstance);
                soundInstance = null;
            } else {
                // Otherwise, start playing the sound on loop
                RandomSource random = RandomSource.create();
                soundInstance = new LoopSound(ModSounds.GEIGER_DETECTION.get(), SoundSource.BLOCKS, player, random);
                Minecraft.getInstance().getSoundManager().play(soundInstance);
            }
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }
}
