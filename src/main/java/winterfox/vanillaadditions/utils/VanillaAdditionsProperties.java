package winterfox.vanillaadditions.utils;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import winterfox.vanillaadditions.registry.ItemRegistry;

public class VanillaAdditionsProperties {
	public static void addProperties() {
		addBowProperty(ItemRegistry.GOLD_BOW.get());
	}
	
    private static void addBowProperty(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (itemstack, level, livingEntity, someNumber) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemstack ? 0.0F : (float)(itemstack.getUseDuration() -
                        livingEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        
        ItemProperties.register(item, new ResourceLocation("pulling"), (itemstack, level, livingEntity, someNumber) -> {
            return livingEntity != null && 
            		livingEntity.isUsingItem() && 
            		livingEntity.getUseItem() == itemstack ? 1.0F : 0.0F;
        });
    }
    

}
