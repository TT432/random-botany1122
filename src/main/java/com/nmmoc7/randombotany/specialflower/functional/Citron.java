package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.book.SpecialFlowerPages;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import vazkii.botania.api.lexicon.LexiconEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class Citron extends BaseFunctionalFlower {
    private final List<BlockPos> needParticle = new ArrayList<>();

    long time;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (getWorld() == null || getWorld().isRemote || getWorld().getWorldTime() == time) {
            return;
        }

        time = getWorld().getWorldTime();

        List<TileEntity> list = searchTileEntity(getRange(), getRange(), null);

        for (TileEntity tileEntity : list) {
            if (!tileEntity.isInvalid() && tileEntity instanceof ITickable) {
                if (mana > cost()) {
                    addMana(-cost());

                    if (!needParticle.contains(tileEntity.getPos())) {
                        needParticle.add(tileEntity.getPos());
                    }

                    ((ITickable) tileEntity).update();
                    ((ITickable) tileEntity).update();
                    ((ITickable) tileEntity).update();
                }
            }
        }

        if (ticksExisted % 40 == 0) {
            needParticle.forEach(p -> {
                ((WorldServer) getWorld()).spawnParticle(EnumParticleTypes.CLOUD,
                        p.getX() + .5, p.getY() + .5, p.getZ() + .5,
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );
            });
            needParticle.clear();
        }
    }

    @Override
    public int getRange() {
        return 3;
    }

    @Override
    public int getMaxMana() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int cost() {
        return 35000;
    }

    @Override
    public LexiconEntry getEntry() {
        return SpecialFlowerPages.CITRON;
    }
}
