package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.items.ItemHandlerHelper;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;
import vazkii.botania.common.lib.LibItemNames;

import java.awt.*;
import java.util.List;

public class TinyPotatoBeliever extends SubTileGenerating {
    private static final int RANGE = 1;

    private static final String TAG_COOLDOWN = "cooldown";
    int cooldown = 0;

    private static final String TAG_COUNT = "count";
    int tinyPotatoCount = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (supertile.getWorld().isRemote)
            return;

        if(cooldown > 0)
            cooldown--;

        if(tinyPotatoCount != 0 && cooldown == 0) {
            if (--tinyPotatoCount != 0)
                cooldown = tinyPotatoCount > 32 ? 50 : 100;

            mana = Math.min(getMaxMana(), mana + 2157);

            getWorld().playSound(null, supertile.getPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.BLOCKS, 1, 0.1F);
            sync();
        }

        int slowdown = getSlowdownFactor();

        List<EntityItem> items = supertile.getWorld()
                .getEntitiesWithinAABB(EntityItem.class,
                        new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE),
                                supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)),
                        (item) -> {
                            ItemStack stack = item.getItem();
                            return !stack.isEmpty() &&
                                    stack.getItem() instanceof ItemBlockTinyPotato &&
                                    !item.isDead &&
                                    item.getAge() >= slowdown;
                        });

        for(EntityItem item : items) {
            ItemStack stack = item.getItem();

            if(tinyPotatoCount <= 64) {
                tinyPotatoCount += stack.getCount();
                item.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.2F, 0.6F);
                sync();
                ((WorldServer) supertile.getWorld()).spawnParticle(EnumParticleTypes.ITEM_CRACK, false,
                        item.posX, item.posY, item.posZ,
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D,
                        Item.getIdFromItem(stack.getItem()), stack.getItemDamage()
                );
            }

            item.setDead();
        }
    }
    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_COOLDOWN, cooldown);
        cmp.setInteger(TAG_COUNT, tinyPotatoCount);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        cooldown = cmp.getInteger(TAG_COOLDOWN);
        tinyPotatoCount = cmp.getInteger(TAG_COUNT);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), RANGE);
    }

    @Override
    public int getMaxMana() {
        return 90000;
    }

    @Override
    public int getColor() {
        return 0xD3D604;
    }

    @Override
    public LexiconEntry getEntry() {
        return ModSpecialFlowers.TINY_POTATO_BELIEVER;
    }
}
