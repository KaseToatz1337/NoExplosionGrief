package com.kasetoatz.noexplosiongrief.mixin;

import com.kasetoatz.noexplosiongrief.config.Config;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method="canExplosionDestroyBlock", at = @At("HEAD"), cancellable = true)
    public void onExplosion(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float explosionPower, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object)this;
        if ((entity instanceof CreeperEntity && Config.blockCreeper) || ((entity instanceof FireballEntity && ((FireballEntity)entity).getOwner() instanceof GhastEntity) && Config.blockGhast) || ((entity instanceof WitherSkullEntity && ((WitherSkullEntity)entity).getOwner() instanceof WitherEntity) && Config.blockWither))
        {
            cir.setReturnValue(false);
        }
    }
}