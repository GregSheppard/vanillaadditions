package winterfox.vanillaadditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.vanillaadditions.VanillaAdditions;

@Mod.EventBusSubscriber(modid = VanillaAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VanillaAdditionsDataGenerator {
	@SubscribeEvent
	public static void registerDataProviders(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper fh = event.getExistingFileHelper();
		boolean forClient = event.includeClient();
		boolean forServer = event.includeServer();
		gen.addProvider(forClient, new VanillaAdditionsLanguageProvider(gen, VanillaAdditions.MODID, "en_us"));
		gen.addProvider(forClient, new VanillaAdditionsRecipeProvider(gen));
		gen.addProvider(forClient, new VanillaAdditionsItemModelProvider(gen, VanillaAdditions.MODID, fh));
		gen.addProvider(forClient, new VanillaAdditionsBlockstateProvider(gen, VanillaAdditions.MODID, fh));
	}
	
}
