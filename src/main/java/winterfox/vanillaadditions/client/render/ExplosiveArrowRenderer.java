package winterfox.vanillaadditions.client.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.entity.ExplosiveArrowEntity;

public class ExplosiveArrowRenderer extends ArrowRenderer<ExplosiveArrowEntity> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(VanillaAdditions.MODID, "textures/entity/explosive_arrow.png");
	
	public ExplosiveArrowRenderer(EntityRendererProvider.Context manager) {
		super(manager);
	}
	
	public ResourceLocation getTextureLocation(ExplosiveArrowEntity arrow) {
		return TEXTURE;
	}
}
