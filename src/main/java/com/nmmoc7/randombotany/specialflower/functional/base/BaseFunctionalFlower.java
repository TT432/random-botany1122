package com.nmmoc7.randombotany.specialflower.functional.base;

import com.nmmoc7.randombotany.book.SpecialFlowerPages;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public abstract class BaseFunctionalFlower extends SubTileFunctional {
    /**
     * 获取作用范围
     *
     * @return range
     */
    abstract public int getRange();

    /**
     * 获取消耗
     *
     * @return cost
     */
    abstract public int cost();

    protected <T extends Entity> List<T> searchEntities(Class<T> clazz, @Nullable Predicate<Entity> filter) {
        return getWorld().getEntitiesWithinAABB(clazz,
                new AxisAlignedBB(getPos().add(-getRange(), -getRange(), -getRange()),
                        getPos().add(getRange() + 1, getRange() + 1, getRange() + 1)),
                (entity) ->
                        entity.isEntityAlive() && (filter == null || filter.test(entity)));
    }

    protected List<TileEntity> searchTileEntity(int w, int h, @Nullable Predicate<TileEntity> filter) {
        List<TileEntity> result = new ArrayList<>();

        BlockPos pos = getPos();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        for (int i = x - w; i <= x + w; i++) {
            for (int k = z - w; k <= z + w; k++) {
                for (int j = y - h; j <= y + h; j++) {
                    TileEntity tile = getWorld().getTileEntity(new BlockPos(i, j, k));

                    if (tile != null && tile != this.supertile && (filter == null || filter.test(tile))) {
                        result.add(tile);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public int getMaxMana() {
        return cost();
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(getPos(), getRange());
    }

    @Override
    public LexiconEntry getEntry() {
        return SpecialFlowerPages.get(getUnlocalizedName());
    }
}
