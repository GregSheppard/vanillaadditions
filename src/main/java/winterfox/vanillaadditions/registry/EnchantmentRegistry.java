package winterfox.vanillaadditions.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import winterfox.vanillaadditions.VanillaAdditions;
import winterfox.vanillaadditions.enchantments.ExplosiveEnchantment;
import winterfox.vanillaadditions.enchantments.LavaWalkerEnchantment;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS =
			DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, VanillaAdditions.MODID);
	
	public static final RegistryObject<Enchantment> EXPLOSIVE = ENCHANTMENTS.register("explosive", ExplosiveEnchantment::new);
	public static final RegistryObject<Enchantment> LAVA_WALKER = ENCHANTMENTS.register("lava_walker", LavaWalkerEnchantment::new);
	//gives hearts no protection
	//
}
