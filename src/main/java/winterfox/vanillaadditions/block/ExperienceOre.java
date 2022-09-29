package winterfox.vanillaadditions.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ExperienceOre extends DropExperienceBlock {

	public ExperienceOre(Properties properties, IntProvider provider) {
		super(properties, provider);
	}
	
	@Override
	@SuppressWarnings( "deprecation" )
	public void playerDestroy(Level level, Player player, BlockPos pos, BlockState blockstate, @Nullable BlockEntity blockEntity, ItemStack stack) {
		if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
			player.awardStat(Stats.BLOCK_MINED.get(this));
			player.causeFoodExhaustion(0.005F);
		    dropResources(blockstate, level, pos, blockEntity, player, stack);
		} else {
			player.awardStat(Stats.BLOCK_MINED.get(this));
			player.causeFoodExhaustion(0.005F);
		}

	}

}
