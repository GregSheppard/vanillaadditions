package winterfox.vanillaadditions.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.client.render.ExplosiveArrowRenderer;
import winterfox.vanillaadditions.client.render.TorchArrowRenderer;
import winterfox.vanillaadditions.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = VanillaAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
	@SubscribeEvent
	public static void doSetup(FMLClientSetupEvent event) {
		EntityRenderers.register(EntityRegistry.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
		EntityRenderers.register(EntityRegistry.TORCH_ARROW.get(), TorchArrowRenderer::new);
	}

}
