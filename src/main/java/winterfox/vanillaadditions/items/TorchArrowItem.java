package winterfox.vanillaadditions.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import winterfox.vanillaadditions.entity.TorchArrowEntity;
import winterfox.vanillaadditions.registry.EntityRegistry;

public class TorchArrowItem extends ArrowItem {
	public TorchArrowItem(Properties props) {
		super(props);
	}
	
	@Override
	public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
		return new TorchArrowEntity(EntityRegistry.TORCH_ARROW.get(), shooter, world);
	}
}
