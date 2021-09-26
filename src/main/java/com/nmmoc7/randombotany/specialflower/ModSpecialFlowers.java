package com.nmmoc7.randombotany.specialflower;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BLexiconCategory;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class ModSpecialFlowers {
    public static final BLexiconCategory CATEGORY_RB = new BLexiconCategory("rb.category", 6);

    public static final String TINY_POTATO_BELIEVER_NAME = "believer";

    public static final LexiconEntry TINY_POTATO_BELIEVER =
            new BasicLexiconEntry(TINY_POTATO_BELIEVER_NAME, CATEGORY_RB)
                    .setPriority()
                    .setLexiconPages(new PageText("1")
                            //, new PagePetalRecipe("2", PetalRecipesBA.wither_flower)
                    );

    public static void init() {
        BotaniaAPI.addCategory(CATEGORY_RB);
        System.out.println(BotaniaAPI.getAllCategories());

        TINY_POTATO_BELIEVER.setIcon(ItemBlockSpecialFlower.ofType(TINY_POTATO_BELIEVER_NAME));
    }
}
