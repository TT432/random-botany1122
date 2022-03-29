package com.nmmoc7.randombotany.book;

import com.nmmoc7.randombotany.recipes.AltarRecipes;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

/**
 * @author DustW
 **/
public class SpecialFlowerPages {
    public static final LexiconEntry TINY_POTATO_BELIEVER = page(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME, AltarRecipes.believer);
    public static final LexiconEntry WITCH = page(ModSpecialFlowers.WITCH_NAME, AltarRecipes.witch);
    public static final LexiconEntry CITRON = page(ModSpecialFlowers.CITRON_NAME, AltarRecipes.citron);

    private static LexiconEntry page(String name, RecipePetals recipe) {
        LexiconEntry result = new BasicLexiconEntry(name, RandomBotanyCategory.CATEGORY_RB)
                .setPriority()
                .setLexiconPages(new PageText("1"),
                        new PagePetalRecipe<>("2", recipe)
                );
        result.setIcon(ItemBlockSpecialFlower.ofType(name));
        return result;
    }

    public static void init() {
    }
}
