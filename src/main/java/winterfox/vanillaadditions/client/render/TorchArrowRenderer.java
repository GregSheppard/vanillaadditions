package winterfox.vanillaadditions.client.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.entity.TorchArrowEntity;

public class TorchArrowRenderer extends ArrowRenderer<TorchArrowEntity> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(VanillaAdditions.MODID, "textures/entity/torch_arrow.png");
	
	public TorchArrowRenderer(EntityRendererProvider.Context manager) {
		super(manager);
	}
	
	public ResourceLocation getTextureLocation(TorchArrowEntity arrow) {
		return TEXTURE;
	}
}
