package winterfox.vanillaadditions.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.items.ExplosiveArrowItem;
import winterfox.vanillaadditions.items.GoldBow;
import winterfox.vanillaadditions.items.TorchArrowItem;

public class ItemRegistry {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, VanillaAdditions.MODID);
	
	
	//Register items to deferred register
	public static final RegistryObject<Item> GOLD_BOW = ITEMS.register("gold_bow", 
			() -> new GoldBow(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance).defaultDurability(150)));

	public static final RegistryObject<Item> STEAK_SANDWICH = ITEMS.register("steak_sandwich",
			() -> new Item(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)
					.food(new FoodProperties.Builder().nutrition(10).saturationMod(15f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0f).build())));
	
	public static final RegistryObject<Item> EXPLOSIVE_ARROW = ITEMS.register("explosive_arrow", 
			() -> new ExplosiveArrowItem(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));
	
	public static final RegistryObject<Item> TORCH_ARROW = ITEMS.register("torch_arrow", 
			() -> new TorchArrowItem(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));

	public static final RegistryObject<Item> HEMP_SEEDS = ITEMS.register("hemp_seeds",
			() -> new ItemNameBlockItem(BlockRegistry.HEMP_CROP.get(), new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));

	public static final RegistryObject<Item> HEMP = ITEMS.register("hemp_string",
			() -> new Item(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));

	public static final RegistryObject<Item> HEMP_FIBRES = ITEMS.register("hemp_fibres",
			() -> new Item(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));

	public static final RegistryObject<Item> ROPE = ITEMS.register("hemp_rope",
			() -> new Item(new Item.Properties().tab(VanillaAdditionsCreativeTab.instance)));
	
	//creative mod tab for mod
	public static class VanillaAdditionsCreativeTab extends CreativeModeTab {
		public static final VanillaAdditionsCreativeTab instance = new VanillaAdditionsCreativeTab(CreativeModeTab.TABS.length, VanillaAdditions.MODID);
		private VanillaAdditionsCreativeTab(int index, String label) {
			super(index, label);
		}
		
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(GOLD_BOW.get());
		}
	}
}
