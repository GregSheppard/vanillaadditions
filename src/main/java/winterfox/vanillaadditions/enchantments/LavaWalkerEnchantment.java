package winterfox.vanillaadditions.enchantments;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.vanillaadditions.registry.EnchantmentRegistry;

public class LavaWalkerEnchantment extends Enchantment {
   public LavaWalkerEnchantment() {
      super(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, 
				new EquipmentSlot[] {EquipmentSlot.FEET});
   }

   @Override
   public int getMinCost(int minCost) {
      return minCost * 10;
   }

   @Override
   public int getMaxCost(int maxCost) {
      return this.getMinCost(maxCost) + 15;
   }

   @Override
   public boolean isTreasureOnly() {
      return false;
   }

   @Override
   public int getMaxLevel() {
      return 2;
   }
   
   @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
   public static class LavaWalkerHandler {
	   @SubscribeEvent
	   public static void place(TickEvent.PlayerTickEvent event) {
		   if(event.phase == TickEvent.Phase.END || !event.player.level.isClientSide()) return;
		      if (event.player.isOnGround()) {
		          BlockPos pos = event.player.blockPosition();
		          LivingEntity player = event.player;
		          Level world = event.player.level;
		    	  int enchantLevel = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistry.LAVA_WALKER.get(), player);
		          BlockState blockstate = Blocks.OBSIDIAN.defaultBlockState();
		          float f = (float)Math.min(enchantLevel, 2*enchantLevel);
		          BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		          for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset((double)(-f), -1.0D, (double)(-f)), pos.offset((double)f, -1.0D, (double)f))) {
		             if (blockpos.closerToCenterThan(player.position(), (double)f)) {
		                blockpos$mutableblockpos.set(blockpos.getX(), blockpos.getY() + 1, blockpos.getZ());
		                BlockState blockstate1 = world.getBlockState(blockpos$mutableblockpos);
		                if (blockstate1.isAir()) {
		                   BlockState blockstate2 = world.getBlockState(blockpos);
		                   boolean isFull = blockstate2.getBlock() == Blocks.LAVA && blockstate2.getValue(LiquidBlock.LEVEL) == 0; //TODO: Forge, modded waters?
		                   if (blockstate2.getMaterial() == Material.LAVA && isFull && blockstate.canSurvive(world, blockpos) && world.isUnobstructed(blockstate, blockpos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(player, net.minecraftforge.common.util.BlockSnapshot.create(world.dimension(), world, blockpos), net.minecraft.core.Direction.UP)) {
		                      world.setBlockAndUpdate(blockpos, blockstate);
		                      world.scheduleTick(blockpos, Blocks.OBSIDIAN, Mth.nextInt(player.getRandom(), 60, 120));
		                   }
		                }
		             }
		          }

		       }
	   }
   }

}
