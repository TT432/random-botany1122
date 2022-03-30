package com.nmmoc7.randombotany.recipes;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class AltarRecipes {
    public static RecipePetals believer;
    public static RecipePetals witch;
    public static RecipePetals citron;
    public static RecipePetals vacuity;

    private static final Map<String, RecipePetals> map = new HashMap<>();

    public static void init() {
        believer = register(
                ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME,
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.black, ModPetalRecipes.black,
                ModPetalRecipes.runeMana, new ItemStack(ModBlocks.tinyPotato)
        );

        witch = register(
                ModSpecialFlowers.WITCH_NAME,
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.purple, ModPetalRecipes.purple,
                ModPetalRecipes.black, ModPetalRecipes.runeMana
        );

        citron = register(
                ModSpecialFlowers.CITRON_NAME,
                ModPetalRecipes.orange, ModPetalRecipes.orange, ModPetalRecipes.orange,
                ModPetalRecipes.orange, ModPetalRecipes.orange, ModPetalRecipes.orange,
                ModPetalRecipes.orange,
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.white,
                ModPetalRecipes.runeMana,
                ModPetalRecipes.runeWrath
        );

        vacuity = register(
                ModSpecialFlowers.VACUITY_NAME,
                ModPetalRecipes.white, ModPetalRecipes.white, ModPetalRecipes.white,
                ModPetalRecipes.white, ModPetalRecipes.white, ModPetalRecipes.white,
                ModPetalRecipes.white,
                ModPetalRecipes.runeMana
        );
    }

    public static RecipePetals get(String name) {
        return map.get(name);
    }

    private static RecipePetals register(String name, Object... items) {
        RecipePetals result = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(name), items);
        map.put(name, result);
        return result;
    }
}
