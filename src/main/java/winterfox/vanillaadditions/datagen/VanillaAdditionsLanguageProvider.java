package winterfox.vanillaadditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import winterfox.vanillaadditions.registry.BlockRegistry;
import winterfox.vanillaadditions.registry.EnchantmentRegistry;
import winterfox.vanillaadditions.registry.ItemRegistry;

public class VanillaAdditionsLanguageProvider extends LanguageProvider {

	public VanillaAdditionsLanguageProvider(DataGenerator gen, String modid, String locale) {
		super(gen, modid, locale);
	}

	@Override
	protected void addTranslations() {
		//Blocks
		this.addBlock(BlockRegistry.DEEPSLATE_EXPERIENCE_ORE, "Deepslate Experience Ore");
		this.addBlock(BlockRegistry.EXPERIENCE_ORE, "Experience Ore");
		this.addBlock(BlockRegistry.NETHERITE_ANVIL, "Netherite Anvil");

		//Items
		this.addItem(ItemRegistry.EXPLOSIVE_ARROW, "Explosive Arrow");
		this.addItem(ItemRegistry.GOLD_BOW, "Gold Bow");
		this.addItem(ItemRegistry.STEAK_SANDWICH, "Steak Sandwich");
		this.addItem(ItemRegistry.TORCH_ARROW, "Torch Arrow");
		this.addItem(ItemRegistry.HEMP, "Hemp");
		this.addItem(ItemRegistry.HEMP_SEEDS, "Hemp Seeds");

		//Enchantments
		this.addEnchantment(EnchantmentRegistry.EXPLOSIVE, "Explosive");
		this.addEnchantment(EnchantmentRegistry.LAVA_WALKER, "Lava Walker");

		//creative tabs
		this.add("itemGroup.vanillaadditions", "Vanilla Additions");

	}

}
