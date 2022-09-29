package winterfox.vanillaadditions.enchantments;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import winterfox.vanillaadditions.registry.EnchantmentRegistry;

public class ExplosiveEnchantment extends Enchantment {
	public ExplosiveEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, 
				new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}
	
	@Override
	public void doPostAttack(LivingEntity attacker, Entity target, int level) {
		int enchantLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.EXPLOSIVE.get(), attacker);
		target.getLevel().explode(target, target.getX(), target.getY(), target.getZ(), 2.0f*enchantLevel, true, Explosion.BlockInteraction.NONE);
	}
	
	@Override
	public boolean isTreasureOnly() {
		return false;
	}
	
	@Override
	public boolean isTradeable() {
		return true;
	}
	
	@Override
	public boolean isAllowedOnBooks() {
		return true;
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}

}
