
package com.example.chernobyl.init;


import com.example.chernobyl.worldgen.carver.CWorldCarvers;
import com.example.chernobyl.worldgen.feature.CBaseFeatures;

public class ModFeatures
{
    public static void setup()
    {
        // Most of these setups do nothing and only serve to make sure initialization has happened at the right time

        // Features
        CBaseFeatures.setup();

        // Carvers
        CWorldCarvers.setup();
    }
}
