package winterfox.vanillaadditions.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.entity.ExplosiveArrowEntity;
import winterfox.vanillaadditions.entity.TorchArrowEntity;

public class EntityRegistry {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, 
			VanillaAdditions.MODID);
	
	public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW = ENTITY_TYPES.register(
			"explosive_arrow",
			() -> EntityType.Builder.of((EntityType.EntityFactory<ExplosiveArrowEntity>) ExplosiveArrowEntity::new,
					MobCategory.MISC).sized(0.5f, 0.5f).build("explosive_arrow"));
	
	public static final RegistryObject<EntityType<TorchArrowEntity>> TORCH_ARROW = ENTITY_TYPES.register(
			"torch_arrow",
			() -> EntityType.Builder.of((EntityType.EntityFactory<TorchArrowEntity>) TorchArrowEntity::new,
					MobCategory.MISC).sized(0.5f, 0.5f).build("torch_arrow"));

}
