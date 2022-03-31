package com.nmmoc7.randombotany.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class AltarRecipes {
    private static final Map<String, RecipePetals> map = new HashMap<>();

    public static void init() {
        register("believer",
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.black, ModPetalRecipes.black,
                ModPetalRecipes.runeMana, new ItemStack(ModBlocks.tinyPotato)
        );

        register("witch",
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.purple, ModPetalRecipes.purple,
                ModPetalRecipes.black, ModPetalRecipes.runeMana
        );

        register("citron",
                ModPetalRecipes.orange, ModPetalRecipes.orange, ModPetalRecipes.orange,
                ModPetalRecipes.orange, ModPetalRecipes.orange, ModPetalRecipes.orange,
                ModPetalRecipes.orange,
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.white,
                ModPetalRecipes.runeMana,
                ModPetalRecipes.runeWrath
        );

        register("vacuity",
                ModPetalRecipes.white, ModPetalRecipes.white, ModPetalRecipes.white,
                ModPetalRecipes.white, ModPetalRecipes.white, ModPetalRecipes.white,
                ModPetalRecipes.white,
                ModPetalRecipes.runeMana
        );

        register("cuseater",
                ModPetalRecipes.red, ModPetalRecipes.red, ModPetalRecipes.red,
                ModPetalRecipes.red, ModPetalRecipes.red, ModPetalRecipes.red,
                ModPetalRecipes.red,
                ModPetalRecipes.lightGray, ModPetalRecipes.lightGray,
                ModPetalRecipes.lightGray,
                ModPetalRecipes.runeGluttony,
                new ItemStack(Items.ENCHANTED_BOOK));

        register("willeater",
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.green, ModPetalRecipes.green,
                ModPetalRecipes.runePride, ModPetalRecipes.runePride,
                new ItemStack(ModItems.ancientWill, 1, 0),
                new ItemStack(ModItems.ancientWill, 1, 1),
                new ItemStack(ModItems.ancientWill, 1, 2),
                new ItemStack(ModItems.ancientWill, 1, 3),
                new ItemStack(ModItems.ancientWill, 1, 4),
                new ItemStack(ModItems.ancientWill, 1, 5),
                new ItemStack(ModItems.manaResource, 1, 5),
                new ItemStack(ModItems.manaResource, 1, 5),
                new ItemStack(ModItems.manaResource, 1, 5),
                new ItemStack(ModItems.manaResource, 1, 5)
        );
    }

    public static RecipePetals get(String name) {
        return map.get(name);
    }

    private static void register(String name, Object... items) {
        RecipePetals result = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(name), items);
        map.put(name, result);
    }
}
