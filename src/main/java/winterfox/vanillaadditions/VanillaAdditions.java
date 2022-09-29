package winterfox.vanillaadditions;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import winterfox.vanillaadditions.recipes.BrewingRecipes;
import winterfox.vanillaadditions.registry.BlockRegistry;
import winterfox.vanillaadditions.registry.ConfiguredFeaturesRegistry;
import winterfox.vanillaadditions.registry.EnchantmentRegistry;
import winterfox.vanillaadditions.registry.EntityRegistry;
import winterfox.vanillaadditions.registry.ItemRegistry;
import winterfox.vanillaadditions.registry.PlacedFeaturesRegistry;
import winterfox.vanillaadditions.utils.VanillaAdditionsProperties;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VanillaAdditions.MODID)
public class VanillaAdditions
{
    public static final String MODID = "vanillaadditions";
    //private static final Logger LOGGER = LogUtils.getLogger();

    public VanillaAdditions()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        ConfiguredFeaturesRegistry.CONFIGURED_FEATURES.register(modEventBus); 
        PlacedFeaturesRegistry.PLACED_FEATURES.register(modEventBus);
        EnchantmentRegistry.ENCHANTMENTS.register(modEventBus);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    	BrewingRecipes.createRecipes();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        	VanillaAdditionsProperties.addProperties();
        }
    }
}
