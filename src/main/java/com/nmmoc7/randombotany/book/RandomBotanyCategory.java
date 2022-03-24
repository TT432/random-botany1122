package com.nmmoc7.randombotany.book;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBeliever;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BLexiconCategory;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class RandomBotanyCategory {
    public static final BLexiconCategory CATEGORY_RB = new BLexiconCategory("rb.main", 6);

    public static void init() {
        BotaniaAPI.addCategory(CATEGORY_RB);
        SpecialFlowerPages.init();
    }
}
