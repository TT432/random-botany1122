package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.auto.RegFlower;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import java.util.List;

/**
 * @author DustW
 **/
@RegFlower
public class Vacuity extends BaseFunctionalFlower {
    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getWorld() != null && !getWorld().isRemote && mana >= cost()) {
            List<EntityLivingBase> list = searchEntities(EntityLivingBase.class, null);

            addMana(-cost());

            list.forEach(e -> {
                e.attackEntityFrom(DamageSource.DROWN, 2.0F);
            });
        }
    }

    @Override
    public int getRange() {
        return 4;
    }

    @Override
    public int cost() {
        return 1000;
    }
}
