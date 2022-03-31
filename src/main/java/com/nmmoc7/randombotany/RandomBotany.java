package com.nmmoc7.randombotany;

import com.nmmoc7.randombotany.auto.JsonGen;
import com.nmmoc7.randombotany.book.RandomBotanyCategory;
import com.nmmoc7.randombotany.recipes.AltarRecipes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

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
        AltarRecipes.init();
        RandomBotanyCategory.init();

        JsonGen.generate();
    }
}
