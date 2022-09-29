package winterfox.vanillaadditions.recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import winterfox.vanillaadditions.registry.BlockRegistry;

public class BrewingRecipes {
	public static void createRecipes() {
		BrewingRecipeRegistry.addRecipe(Ingredient.of(Items.GLASS_BOTTLE), Ingredient.of(BlockRegistry.EXPERIENCE_ORE.get()), new ItemStack(Items.EXPERIENCE_BOTTLE));
	}
}
