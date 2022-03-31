package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.auto.RegFlower;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import vazkii.botania.common.item.ItemAncientWill;

import java.util.List;

/**
 * @author DustW
 **/
@RegFlower
public class Willeater extends BaseGeneratingFlower {
    Item lastWill;
    int count;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getWorld() == null || getWorld().isRemote) {
            return;
        }

        List<EntityItem> list = searchItems(e -> e.getItem().getItem() instanceof ItemAncientWill);

        for (EntityItem e : list) {
            Item item = e.getItem().getItem();

            if (item == lastWill) {
                count++;
            }
            else {
                lastWill = item;
                count = 1;
            }

            addMana((int) (2400 * Math.pow(1.3, count - 1)));
            e.setDead();
            break;
        }
    }

    @Override
    public int getRange() {
        return 2;
    }
}
