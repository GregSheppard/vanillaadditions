package winterfox.vanillaadditions.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import winterfox.vanillaadditions.entity.ExplosiveArrowEntity;
import winterfox.vanillaadditions.registry.EntityRegistry;

public class ExplosiveArrowItem extends ArrowItem {
	public ExplosiveArrowItem(Properties props) {
		super(props);
	}
	
	@Override
	public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
		return new ExplosiveArrowEntity(EntityRegistry.EXPLOSIVE_ARROW.get(), shooter, world);
	}
}
