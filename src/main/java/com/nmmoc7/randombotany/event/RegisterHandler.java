package com.nmmoc7.randombotany.event;

import com.nmmoc7.randombotany.RandomBotany;
import com.nmmoc7.randombotany.auto.ClassScanner;
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

import java.util.Map;

@Mod.EventBusSubscriber
public class RegisterHandler {
    static Map<String, Class<? extends SubTileEntity>> map = ClassScanner.getFlowers();

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        map.forEach(RegisterHandler::registerSubTile);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event) {
        map.forEach(RegisterHandler::registerModel);
    }

    private static void registerSubTile(String name, Class<? extends SubTileEntity> classF) {
        BotaniaAPI.registerSubTile(name, classF);
        BotaniaAPI.registerSubTileSignature(classF, new BasicSignature(name));
        BotaniaAPI.addSubTileToCreativeMenu(name);
    }

    @SideOnly(Side.CLIENT)
    private static void registerModel(String name, Class<? extends SubTileEntity> clazz) {
        BotaniaAPIClient.registerSubtileModel(clazz, getResourceLocation(name));
    }

    @SideOnly(Side.CLIENT)
    public static ModelResourceLocation getResourceLocation(String name) {
        return new ModelResourceLocation(RandomBotany.MOD_ID + ":" + name);
    }
}
