package winterfox.vanillaadditions.block;

import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;

public class OnlyFenceBlock extends FenceBlock {

	public OnlyFenceBlock(Properties properties) {
		super(properties);
	}
	
	public static boolean isExceptionForConnection(BlockState connectingBlock) {
		return !(connectingBlock.getBlock() instanceof FenceBlock);
	}
	
	@Override
	public boolean connectsTo(BlockState connectingBlock, boolean p_53331_, Direction dir) {
		Block block = connectingBlock.getBlock();
		boolean flag = this.isSameFence(connectingBlock);
		boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(connectingBlock, dir);
		return !isExceptionForConnection(connectingBlock) && p_53331_ || flag || flag1;
	}
	
	private boolean isSameFence(BlockState p_153255_) {
		return p_153255_.is(BlockTags.FENCES) && p_153255_.is(BlockTags.WOODEN_FENCES) == this.defaultBlockState().is(BlockTags.WOODEN_FENCES);
	}

}
