package winterfox.vanillaadditions.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrow{
	public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
		super(entityType, world);
	}
	
	public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
		super(entityType, x, y, z, world);
	}
	
	public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter,  Level world) {
		super(entityType, shooter, world);
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}
	
	@Override
	protected void onHitEntity(EntityHitResult ray) {
		if(ray.getEntity() instanceof Creeper) {
			Creeper creeper = (Creeper)ray.getEntity();
			creeper.ignite();
			//TODO: make this not jank as fuck
			for(int i = 0; i < 31; i++) {
				creeper.tick();
			}
			this.discard();
		} else {
			super.onHitEntity(ray);
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0f, true, Explosion.BlockInteraction.BREAK);
		}
	}

	
	@Override
	protected void onHitBlock(BlockHitResult ray) {
		super.onHitBlock(ray);
	}
	
	@Override
	protected void tickDespawn() {
		if(this.inGroundTime > 0) {
			this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0f, true, Explosion.BlockInteraction.BREAK);
			this.discard();
		}
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
