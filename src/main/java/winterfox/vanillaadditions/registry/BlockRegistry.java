package winterfox.vanillaadditions.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.block.HempCropBlock;
import winterfox.vanillaadditions.block.NetheriteAnvil;
import winterfox.vanillaadditions.block.OnlyFenceBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			VanillaAdditions.MODID);
	
	public static final RegistryObject<Block> EXPERIENCE_ORE = BLOCKS.register("experience_ore",
			() -> new DropExperienceBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(4f, 4f).lightLevel((p_152692_) -> { return 5; }), UniformInt.of(10, 16)));
	public static final RegistryObject<Block> DEEPSLATE_EXPERIENCE_ORE = BLOCKS.register("deepslate_experience_ore",
			() -> new DropExperienceBlock(Block.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(4f, 4f).lightLevel((p_152692_) -> { return 5; }), UniformInt.of(12, 18)));
	public static final RegistryObject<Block> NETHERITE_ANVIL = BLOCKS.register("netherite_anvil", 
			() -> new NetheriteAnvil(Block.Properties.of(Material.HEAVY_METAL).requiresCorrectToolForDrops().strength(50.0F, 1200.0F).sound(SoundType.ANVIL)));
	
	public static final RegistryObject<Block> ONLYFENCE_OAK = BLOCKS.register("onlyfence_oak", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.OAK_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_ACACIA = BLOCKS.register("onlyfence_acacia", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.ACACIA_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_DARK_OAK = BLOCKS.register("onlyfence_dark_oak", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.DARK_OAK_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_SPRUCE = BLOCKS.register("onlyfence_spruce", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.SPRUCE_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_BIRCH = BLOCKS.register("onlyfence_birch", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.BIRCH_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_JUNGLE = BLOCKS.register("onlyfence_jungle", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.JUNGLE_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_CRIMSON = BLOCKS.register("onlyfence_crimson", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.CRIMSON_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_WARPED = BLOCKS.register("onlyfence_warped", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.WARPED_FENCE));
	public static final RegistryObject<Block> ONLYFENCE_MANGROVE = BLOCKS.register("onlyfence_mangrove", 
			() -> new OnlyFenceBlock(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD), Blocks.MANGROVE_FENCE));

	public static final RegistryObject<Block> HEMP_CROP = BLOCKS.register("hemp",
			() -> new HempCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));
	
	@SubscribeEvent
	public static void onRegisterItems(final RegisterEvent event) {
		if(event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
			List<Block> noCreativeTab = new ArrayList<Block>() {
				{
					add(ONLYFENCE_JUNGLE.get());
					add(ONLYFENCE_MANGROVE.get());
					add(ONLYFENCE_SPRUCE.get());
					add(ONLYFENCE_BIRCH.get());
					add(ONLYFENCE_CRIMSON.get());
					add(ONLYFENCE_ACACIA.get());
					add(ONLYFENCE_OAK.get());
					add(ONLYFENCE_WARPED.get());
					add(ONLYFENCE_DARK_OAK.get());
					add(HEMP_CROP.get());
				}
			};
			BLOCKS.getEntries().forEach((blockRegistryObject) -> {
				Block block = blockRegistryObject.get();
				Item.Properties properties = new Item.Properties();
				if(!noCreativeTab.contains(block)) {
					properties.tab(ItemRegistry.VanillaAdditionsCreativeTab.instance);
				}
				Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
				event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
			});
		}
	}
}
