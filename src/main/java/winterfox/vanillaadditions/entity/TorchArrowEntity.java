package winterfox.vanillaadditions.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import winterfox.vanillaadditions.registry.ItemRegistry;

public class TorchArrowEntity extends AbstractArrow {
	private SoundEvent soundEvent = SoundEvents.WOOD_PLACE;
	
	public TorchArrowEntity(EntityType<TorchArrowEntity> entityType, Level world) {
		super(entityType, world);
	}
	
	public TorchArrowEntity(EntityType<TorchArrowEntity> entityType, double x, double y, double z, Level world) {
		super(entityType, x, y, z, world);
	}
	
	public TorchArrowEntity(EntityType<TorchArrowEntity> entityType, LivingEntity shooter,  Level world) {
		super(entityType, shooter, world);
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}
	
	@Override
	protected void onHitEntity(EntityHitResult ray) {
		super.onHitEntity(ray);
		Entity entity = ray.getEntity();
		entity.setSecondsOnFire(20);
		this.discard();
	}

	
	@Override
	protected void onHitBlock(BlockHitResult ray) {
		super.onHitBlock(ray);
		Direction dir = ray.getDirection();
		if(dir == Direction.UP) {
			this.getLevel().setBlockAndUpdate(ray.getBlockPos().above(), Blocks.TORCH.defaultBlockState());
			this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		} else if (dir == Direction.DOWN) {
			this.spawnAtLocation(new ItemStack(ItemRegistry.TORCH_ARROW.get()), 0.1F);
		} else {
			this.getLevel().setBlockAndUpdate(getPosDirectionFromDirection(ray.getBlockPos(), ray.getDirection()), Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, ray.getDirection()));
			this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		}
		this.discard();
	}
	
	private BlockPos getPosDirectionFromDirection(BlockPos pos, Direction d) {
		switch(d) {
		case EAST:
			return pos.east();
		case WEST:
			return pos.west();
		case SOUTH:
			return pos.south();
		case NORTH:
			return pos.north();
		case UP:
			return pos.above();
		case DOWN:
			return pos.below();
		default:
			return pos;
		}
	}
	
	@Override
	protected void tickDespawn() {
		if(this.inGroundTime > 0) {
			this.discard();
		}
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
