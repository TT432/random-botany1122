package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.auto.RegFlower;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPotion;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * @author DustW
 **/
@RegFlower
public class Witch extends BaseGeneratingFlower {
    private static final String TAG_COOLDOWN = "cooldown";
    private int cooldown;

    private Potion lastEffectType = null;
    private int lastEffectTypeAmount = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getWorld() == null || getWorld().isRemote) {
            return;
        }

        cooldown = Math.max(cooldown - 1, 0);

        if (cooldown <= 0) {
            List<EntityItem> items = searchItems(itemEntity ->
                    itemEntity.getItem().getItem() instanceof ItemPotion &&
                            itemEntity.getAge() > getSlowdownFactor());

            for (EntityItem item : items) {
                List<PotionEffect> potions = PotionUtils.getEffectsFromStack(item.getItem());

                if (potions.isEmpty()) {
                    continue;
                }

                PotionEffect potion = potions.get(0);
                int mana = 4000;

                if (lastEffectType == null) {
                    lastEffectType = potion.getPotion();
                    lastEffectTypeAmount = 1;
                } else if (lastEffectType == potion.getPotion()) {
                    mana /= ++lastEffectTypeAmount;
                }

                addMana(mana);

                item.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 0.2F, 0.6F);
                sync();
                ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.PORTAL,
                        item.posX, item.posY, item.posZ,
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );

                item.setDead();
            }
        }
    }

    @Override
    public int getSlowdownFactor() {
        return 5;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_COOLDOWN, cooldown);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        cooldown = cmp.getInteger(TAG_COOLDOWN);
    }

    @Override
    public int getColor() {
        return 0xD3D604;
    }

    @Override
    public int getRange() {
        return 2;
    }
}
