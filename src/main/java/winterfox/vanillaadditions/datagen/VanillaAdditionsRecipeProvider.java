package winterfox.vanillaadditions.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import winterfox.vanillaadditions.registry.BlockRegistry;
import winterfox.vanillaadditions.registry.ItemRegistry;

import java.util.function.Consumer;

public class VanillaAdditionsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    
    public VanillaAdditionsRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {

        //shaped
        ShapedRecipeBuilder.shaped(BlockRegistry.NETHERITE_ANVIL.get())
                .define('B', Blocks.NETHERITE_BLOCK)
                .define('I', Items.NETHERITE_INGOT)
                .pattern("BBB")
                .pattern(" I ")
                .pattern("III")
                .unlockedBy("has_netherite_block", inventoryTrigger(ItemPredicate.Builder.item().of(Items.NETHERITE_BLOCK).build()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegistry.TORCH_ARROW.get())
                .define('F', Items.FEATHER)
                .define('S', Items.STICK)
                .define('C', Ingredient.of(Items.COAL, Items.CHARCOAL))
                .pattern("C  ")
                .pattern(" S ")
                .pattern("  F")
                .unlockedBy("has_fuel", inventoryTrigger(ItemPredicate.Builder.item().of(Items.COAL, Items.CHARCOAL).build()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegistry.EXPLOSIVE_ARROW.get())
                .define('F', Items.FEATHER)
                .define('S', Items.STICK)
                .define('C', Items.FIRE_CHARGE)
                .pattern("C  ")
                .pattern(" S ")
                .pattern("  F")
                .unlockedBy("has_fire_charge", inventoryTrigger(ItemPredicate.Builder.item().of(Items.FIRE_CHARGE).build()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(ItemRegistry.GOLD_BOW.get())
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STRING)
                .pattern(" GS")
                .pattern("G S")
                .pattern(" GS")
                .unlockedBy("has_gold", inventoryTrigger(ItemPredicate.Builder.item().of(Items.GOLD_INGOT).build()))
                .save(finishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(Items.SADDLE)
                .define('L', Items.LEATHER)
                .define('B', Items.IRON_BLOCK)
                .define('S', Items.STRING)
                .define('N', Items.IRON_NUGGET)
                .pattern("LLL")
                .pattern("SBS")
                .pattern("N N")
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item().of(Items.LEATHER).build()))
                .save(finishedRecipeConsumer);

        //shapeless
        ShapelessRecipeBuilder.shapeless(ItemRegistry.STEAK_SANDWHICH.get())
                .requires(Items.BREAD)
                .requires(Items.COOKED_BEEF)
                .unlockedBy("has_bread", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BREAD).build()))
                .save(finishedRecipeConsumer);
    }
}
