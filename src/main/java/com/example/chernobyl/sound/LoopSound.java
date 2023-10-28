package com.example.chernobyl.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;

public class LoopSound extends AbstractTickableSoundInstance {

    private final Player player;

    public LoopSound(SoundEvent sound, SoundSource source, Player player, RandomSource random) {
        super(sound, source, random);
        this.player = player;
        this.looping = true;
        this.x = (float) player.getX();
        this.y = (float) player.getY();
        this.z = (float) player.getZ();
        this.volume = 1.0F; // Default volume
        this.pitch = 1.0F; // Default pitch
    }

    @Override
    public void tick() {
        if (player.isRemoved()) {
            this.stop();
        } else {
            this.x = (float) player.getX();
            this.y = (float) player.getY();
            this.z = (float) player.getZ();
        }
    }
}
