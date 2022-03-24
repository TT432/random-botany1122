package com.nmmoc7.randombotany.event;

import com.nmmoc7.randombotany.RandomBotany;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBeliever;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.signature.BasicSignature;

@Mod.EventBusSubscriber
public class RegisterHandler {
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        registerSubTile(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME, TinyPotatoBeliever.class);
    }

    static void registerSubTile(String name, Class<? extends SubTileEntity> classF) {
        BotaniaAPI.registerSubTile(name, classF);
        BotaniaAPI.registerSubTileSignature(classF, new BasicSignature(name));
        BotaniaAPI.addSubTileToCreativeMenu(name);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event) {
        BotaniaAPIClient.registerSubtileModel(TinyPotatoBeliever.class, getResourceLocation(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME));
    }

    public static ModelResourceLocation getResourceLocation(String name) {
        return new ModelResourceLocation(RandomBotany.MOD_ID + ":" + name);
    }
}
