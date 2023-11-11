package net.dragonMaster.sky_technology_utils.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class YeetBlock extends Block {
    public YeetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.teleportTo(livingEntity.getBlockX(),livingEntity.getBlockY()+ RandomSource.createNewThreadLocalInstance().nextInt(200),livingEntity.getBlockZ());
             }


        super.stepOn(level, blockPos, blockState, entity);
    }
}
