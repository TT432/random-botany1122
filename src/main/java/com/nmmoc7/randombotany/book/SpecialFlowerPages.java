package com.nmmoc7.randombotany.book;

import com.nmmoc7.randombotany.recipes.AltarRecipes;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBeliever;
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
    public static final LexiconEntry TINY_POTATO_BELIEVER =
            new BasicLexiconEntry(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME, RandomBotanyCategory.CATEGORY_RB)
                    .setPriority()
                    .setLexiconPages(new PageText("1"),
                            new PagePetalRecipe<>("2", AltarRecipes.believer)
                    );

    public static void init() {
        TINY_POTATO_BELIEVER.setIcon(ItemBlockSpecialFlower.ofType(ModSpecialFlowers.TINY_POTATO_BELIEVER_NAME));
    }
}
