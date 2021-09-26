package com.nmmoc7.randombotany;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = RandomBotany.MOD_ID,
        name = RandomBotany.MOD_NAME,
        version = RandomBotany.VERSION
)
public class RandomBotany {

    public static final String MOD_ID = "random-botany";
    public static final String MOD_NAME = "Random Botany";
    public static final String VERSION = "r1.12.2-1-1";

    @Mod.Instance(MOD_ID)
    public static RandomBotany INSTANCE;

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        ModSpecialFlowers.init();
    }
}
