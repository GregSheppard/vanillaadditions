package winterfox.vanillaadditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import winterfox.vanillaadditions.registry.BlockRegistry;

public class VanillaAdditionsLanguageProvider extends LanguageProvider{

	public VanillaAdditionsLanguageProvider(DataGenerator gen, String modid, String locale) {
		super(gen, modid, locale);
	}

	@Override
	protected void addTranslations() {
		this.addBlock(BlockRegistry.DEEPSLATE_EXPERIENCE_ORE, "Deepslate Experience Ore");		
	}

}
