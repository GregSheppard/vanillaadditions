package winterfox.vanillaadditions.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.vanillaadditions.registry.ItemRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnGrassBreak {

    @SubscribeEvent
    public static void onGrassBreak(BlockEvent.BreakEvent event) {
        if(event.getLevel().getBlockState(event.getPos()) == Blocks.GRASS.defaultBlockState()) {
            event.getLevel().getBlockState(event.getPos()).spawnAfterBreak(event.getLevel().getServer().overworld(), event.getPos(), new ItemStack(ItemRegistry.HEMP_SEEDS.get()), true);
        }
    }
}
