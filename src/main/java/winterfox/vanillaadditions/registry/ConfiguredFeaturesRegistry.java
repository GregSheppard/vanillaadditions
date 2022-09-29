package winterfox.vanillaadditions.registry;

import java.util.List;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;

public class ConfiguredFeaturesRegistry {
	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
			DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, VanillaAdditions.MODID);
	
	private static final Supplier<List<OreConfiguration.TargetBlockState>> EXPERIENCE_ORE_REPLACEMENT =
			Suppliers.memoize(() -> List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegistry.EXPERIENCE_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegistry.DEEPSLATE_EXPERIENCE_ORE.get().defaultBlockState())
			));
	
	public static final RegistryObject<ConfiguredFeature<?, ?>> EXPERIENCE_ORE = CONFIGURED_FEATURES.register(
			"experience_ore",
			() -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(EXPERIENCE_ORE_REPLACEMENT.get(), 11)));
	
	
}
