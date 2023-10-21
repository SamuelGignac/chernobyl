
package com.example.chernobyl.init;


import com.example.chernobyl.Chernobyl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
    public static void setup()
    {
        registerSounds();
    }

    public static void registerSounds()
    {
        //TODO: Register sounds here
    }

    private static RegistryObject<SoundEvent> registerSound(String name)
    {
        ResourceLocation location = new ResourceLocation(Chernobyl.MODID, name);
        SoundEvent event = SoundEvent.createVariableRangeEvent(location);
        return Chernobyl.SOUND_EVENT_REGISTER.register(name, () -> event);
    }
}