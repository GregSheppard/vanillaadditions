package winterfox.vanillaadditions.items;

import java.util.function.Predicate;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class GoldBow extends ProjectileWeaponItem implements Vanishable {
	   public static final int MAX_DRAW_DURATION = 20;
	   public static final int DEFAULT_RANGE = 15;

	   public GoldBow(Item.Properties properties) {
	      super(properties);
	   }

	   @SuppressWarnings("deprecation")
	   public void releaseUsing(ItemStack bowStack, Level level, LivingEntity entityLiving, int holdTime) {
	      if (entityLiving instanceof Player player) {
	         boolean hasInfinity = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0;
	         ItemStack itemstack = player.getProjectile(bowStack);

	         int charge = this.getUseDuration(bowStack) - holdTime;
	         charge = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, level, player, charge, !itemstack.isEmpty() || hasInfinity);
	         if (charge < 0) return;

	         if (!itemstack.isEmpty() || hasInfinity) {
	            if (itemstack.isEmpty()) {
	               itemstack = new ItemStack(Items.ARROW);
	            }

	            float f = getPowerForTime(charge);
	            if (!((double)f < 0.1D)) {
	               boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, bowStack, player));
	               if (!level.isClientSide) {
	                  ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
	                  AbstractArrow abstractarrow = arrowitem.createArrow(level, itemstack, player);
	                  abstractarrow = customArrow(abstractarrow);
	                  abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
	                  if (f == 1.0F) {
	                     abstractarrow.setCritArrow(true);
	                  }

	                  int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
	                  abstractarrow.setBaseDamage(abstractarrow.getBaseDamage()/2 + (double)j * 0.5D + 0.5D);

	                  int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
	                  if (k > 0) {
	                     abstractarrow.setKnockback(k);
	                  }

	                  if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
	                     abstractarrow.setSecondsOnFire(100);
	                  }

	                  bowStack.hurtAndBreak(1, player, (p_40665_) -> {
	                     p_40665_.broadcastBreakEvent(player.getUsedItemHand());
	                  });
	                  if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
	                     abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
	                  }

	                  level.addFreshEntity(abstractarrow);
	               }

	               level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	               if (!flag1 && !player.getAbilities().instabuild) {
	                  itemstack.shrink(1);
	                  if (itemstack.isEmpty()) {
	                     player.getInventory().removeItem(itemstack);
	                  }
	               }

	               player.awardStat(Stats.ITEM_USED.get(this));
	            }
	         }
	      }
	   }

	   public static float getPowerForTime(int charge) {
	      float f = (float)charge / 20.0F;
	      f = (f * f + f * 2.0F);
	      if (f > 1.0F) {
	         f = 1.0F;
	      }

	      return f;
	   }

	   public int getUseDuration(ItemStack bowStack) {
	      return 72000;
	   }

	   public UseAnim getUseAnimation(ItemStack bowStack) {
	      return UseAnim.BOW;
	   }

	   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
	      ItemStack itemstack = player.getItemInHand(hand);
	      boolean flag = !player.getProjectile(itemstack).isEmpty();

	      InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, level, player, hand, flag);
	      if (ret != null) return ret;

	      if (!player.getAbilities().instabuild && !flag) {
	         return InteractionResultHolder.fail(itemstack);
	      } else {
	         player.startUsingItem(hand);
	         return InteractionResultHolder.consume(itemstack);
	      }
	   }

	   public Predicate<ItemStack> getAllSupportedProjectiles() {
	      return ARROW_ONLY;
	   }

	   public AbstractArrow customArrow(AbstractArrow arrow) {
	      return arrow;
	   }

	   public int getDefaultProjectileRange() {
	      return 15;
	   }
	   
	   @Override
	   public boolean isRepairable(ItemStack stack) {
		   return true;
	   }
	   
	   @Override
	   public boolean isValidRepairItem(ItemStack item, ItemStack material) {
		   return material.getItem() == Items.GOLD_INGOT;
	   }
	
}
