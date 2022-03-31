package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.auto.RegFlower;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.WorldServer;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;

import java.util.List;

@RegFlower
public class Believer extends BaseGeneratingFlower {
    private static final String TAG_COOLDOWN = "cooldown";
    int cooldown = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (supertile.getWorld().isRemote) {
            return;
        }

        if (cooldown > 0) {
            cooldown--;
        }

        int slowdown = getSlowdownFactor();

        List<EntityItem> items = searchItems((item) -> {
            ItemStack stack = item.getItem();
            return !stack.isEmpty() &&
                    stack.getItem() instanceof ItemBlockTinyPotato &&
                    !item.isDead &&
                    item.getAge() >= slowdown;
        });

        for (EntityItem item : items) {
            ItemStack stack = item.getItem();

            stack.shrink(1);

            mana += 2157;
            cooldown += 10;

            item.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.2F, 0.6F);
            ((WorldServer) supertile.getWorld()).spawnParticle(EnumParticleTypes.ITEM_CRACK, false,
                    item.posX, item.posY, item.posZ,
                    20,
                    0.1D, 0.1D, 0.1D,
                    0.05D,
                    Item.getIdFromItem(stack.getItem()), stack.getItemDamage()
            );

            sync();
        }
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
        return 1;
    }
}
