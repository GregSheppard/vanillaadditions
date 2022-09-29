package winterfox.vanillaadditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import winterfox.vanillaadditions.VanillaAdditions;

@Mod.EventBusSubscriber(modid = VanillaAdditions.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class VanillaAdditionsDataGenerator {
	@SubscribeEvent
	public static void registerDataProviders(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		boolean forClient = event.includeClient();
		boolean forServer = event.includeServer();
		generator.addProvider(forClient, new VanillaAdditionsLanguageProvider(generator, VanillaAdditions.MODID, "en_us"));
	}
	
}
