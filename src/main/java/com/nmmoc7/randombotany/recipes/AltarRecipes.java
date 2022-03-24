package com.nmmoc7.randombotany.recipes;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBeliever;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

/**
 * @author DustW
 **/
public class AltarRecipes {
    public static RecipePetals believer;

    public static void init() {
        believer = BotaniaAPI.registerPetalRecipe(
                ItemBlockSpecialFlower.ofType(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME),
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.black, ModPetalRecipes.black,
                ModPetalRecipes.runeMana, new ItemStack(ModBlocks.tinyPotato)
        );
    }
}
