package winterfox.vanillaadditions.event;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.vanillaadditions.registry.BlockRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnlyFenceHandler {
	static Map<Block, Block> fence = new HashMap<Block, Block>();
	
	private static void createMap() {
		fence.put(Blocks.ACACIA_FENCE, BlockRegistry.ONLYFENCE_ACACIA.get());
		fence.put(Blocks.BIRCH_FENCE, BlockRegistry.ONLYFENCE_BIRCH.get());
		fence.put(Blocks.CRIMSON_FENCE, BlockRegistry.ONLYFENCE_CRIMSON.get());
		fence.put(Blocks.DARK_OAK_FENCE, BlockRegistry.ONLYFENCE_DARK_OAK.get());
		fence.put(Blocks.JUNGLE_FENCE, BlockRegistry.ONLYFENCE_JUNGLE.get());
		fence.put(Blocks.MANGROVE_FENCE, BlockRegistry.ONLYFENCE_MANGROVE.get());
		fence.put(Blocks.OAK_FENCE, BlockRegistry.ONLYFENCE_OAK.get());
		fence.put(Blocks.SPRUCE_FENCE, BlockRegistry.ONLYFENCE_SPRUCE.get());
		fence.put(Blocks.WARPED_FENCE, BlockRegistry.ONLYFENCE_WARPED.get());
		
		fence.put(BlockRegistry.ONLYFENCE_ACACIA.get(), Blocks.ACACIA_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_BIRCH.get(), Blocks.BIRCH_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_CRIMSON.get(), Blocks.CRIMSON_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_DARK_OAK.get(), Blocks.DARK_OAK_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_JUNGLE.get(), Blocks.JUNGLE_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_MANGROVE.get(), Blocks.MANGROVE_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_OAK.get(), Blocks.OAK_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_SPRUCE.get(), Blocks.SPRUCE_FENCE);
		fence.put(BlockRegistry.ONLYFENCE_WARPED.get(), Blocks.WARPED_FENCE);
	}
	
	@SubscribeEvent
	public static void handleAxesOnFence(PlayerInteractEvent.RightClickBlock event) {
		if(fence.isEmpty()) { createMap(); }
			if(event.getItemStack().getItem() instanceof AxeItem) {
				if(event.getLevel().getBlockState(event.getPos()).getBlock() instanceof FenceBlock) {
					FenceBlock rightClicked = (FenceBlock)event.getLevel().getBlockState(event.getPos()).getBlock();
					event.getLevel().setBlockAndUpdate(event.getPos(), setBlockStateFromBlock(event, (FenceBlock)fence.get(rightClicked)));
					event.getEntity().playSound(SoundEvents.WOOD_BREAK);
				}
			}
	}
	
	public static BlockState setBlockStateFromBlock(PlayerInteractEvent.RightClickBlock event, FenceBlock block) {
		BlockPos west = event.getPos().west();
		BlockState westState = event.getLevel().getBlockState(west);
		BlockPos east = event.getPos().east();
		BlockState eastState = event.getLevel().getBlockState(east);
		BlockPos north = event.getPos().north();
		BlockState northState = event.getLevel().getBlockState(north);
		BlockPos south = event.getPos().south();
		BlockState southState = event.getLevel().getBlockState(south);
		BlockState state = block.defaultBlockState()
				.setValue(FenceBlock.NORTH, Boolean.valueOf(block.connectsTo(northState, northState.isFaceSturdy(event.getLevel(), north, Direction.SOUTH), Direction.SOUTH)))
				.setValue(FenceBlock.EAST, Boolean.valueOf(block.connectsTo(eastState, eastState.isFaceSturdy(event.getLevel(), east, Direction.WEST), Direction.WEST)))
				.setValue(FenceBlock.WEST, Boolean.valueOf(block.connectsTo(westState, westState.isFaceSturdy(event.getLevel(), west, Direction.EAST), Direction.EAST)))
				.setValue(FenceBlock.SOUTH, Boolean.valueOf(block.connectsTo(southState, southState.isFaceSturdy(event.getLevel(), south, Direction.NORTH), Direction.NORTH))); 
		return state;
	}
	
}
