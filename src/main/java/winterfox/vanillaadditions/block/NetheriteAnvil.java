package winterfox.vanillaadditions.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NetheriteAnvil extends AnvilBlock {

	public NetheriteAnvil(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	public static BlockState damage(BlockState blockState) {
		return blockState;
	}
	
	@Override
	public MenuProvider getMenuProvider(BlockState blockState, Level world, BlockPos p_48823_) {
		return new SimpleMenuProvider((p_48785_, p_48786_, p_48787_) -> {
			return new AnvilMenu(p_48785_, p_48786_, ContainerLevelAccess.create(world, p_48823_));
		}, Component.translatable("container.repair"));
	}
	
	@Override
	protected void falling(FallingBlockEntity fallingBlock) {
		fallingBlock.setHurtsEntities(4.0F, 80);
	}

}
