package com.nmmoc7.randombotany.book;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.lexicon.BLexiconCategory;

public class RandomBotanyCategory {
    public static final BLexiconCategory CATEGORY_RB = new BLexiconCategory("rb.main", 6);

    public static void init() {
        BotaniaAPI.addCategory(CATEGORY_RB);
        SpecialFlowerPages.init();
    }
}
