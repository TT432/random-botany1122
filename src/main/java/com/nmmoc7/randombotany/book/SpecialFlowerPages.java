package com.nmmoc7.randombotany.book;

import com.nmmoc7.randombotany.recipes.AltarRecipes;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public class SpecialFlowerPages {
    private static final Map<String, LexiconEntry> map = new HashMap<>();

    public static LexiconEntry get(String name) {
        return map.getOrDefault(name, page(name));
    }

    private static LexiconEntry page(String name) {
        LexiconEntry result = new BasicLexiconEntry(name, RandomBotanyCategory.CATEGORY_RB)
                .setPriority()
                .setLexiconPages(new PageText("1"),
                        new PagePetalRecipe<>("2", AltarRecipes.get(name))
                );
        result.setIcon(ItemBlockSpecialFlower.ofType(name));
        map.put(name, result);
        return result;
    }

    public static void init() {
    }
}
