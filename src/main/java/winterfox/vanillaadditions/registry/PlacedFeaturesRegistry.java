package winterfox.vanillaadditions.registry;

import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;

public class PlacedFeaturesRegistry {
	
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = 
			DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, VanillaAdditions.MODID);
	
	public static final RegistryObject<PlacedFeature> EXPERIENCE_ORE = PLACED_FEATURES.register("experience_ore",
			() -> new PlacedFeature(ConfiguredFeaturesRegistry.EXPERIENCE_ORE.getHolder().get(),
					commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.absolute(40)))));
	
	private static List<PlacementModifier> commonOrePlacement(int countPerChunk, PlacementModifier height) {
		return orePlacement(CountPlacement.of(countPerChunk), height);
	}
	
	private static List<PlacementModifier> orePlacement(PlacementModifier count, PlacementModifier height) {
		return List.of(count, InSquarePlacement.spread(), height, BiomeFilter.biome());
	}

}
