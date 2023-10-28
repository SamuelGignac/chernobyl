package com.example.chernobyl.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RadiationEffect extends MobEffect {

    public RadiationEffect(MobEffectCategory mobEffectCategory, int color) {

        super(mobEffectCategory, color);

    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 1.0F + pAmplifier * 0.5F);

        super.applyEffectTick(pLivingEntity, pAmplifier);

    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
