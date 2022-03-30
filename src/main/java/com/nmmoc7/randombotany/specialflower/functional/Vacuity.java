package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.auto.RegFlower;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.enchantment.EnchantmentHelper;
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
                int air = e.getAir();
                air = decreaseAirSupply(e, air);
                e.setAir(air);
                if (e.getAir() <= -20) {
                    e.setAir(0);
                    e.attackEntityFrom(DamageSource.DROWN, 2.0F);
                }
            });
        }
    }

    int decreaseAirSupply(EntityLivingBase e, int air) {
        int i = EnchantmentHelper.getRespirationModifier(e);
        return i > 0 && getWorld().rand.nextInt(i + 1) > 0 ? air : air - 5;
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
