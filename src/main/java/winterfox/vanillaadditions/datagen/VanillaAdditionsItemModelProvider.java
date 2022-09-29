package winterfox.vanillaadditions.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import winterfox.vanillaadditions.registry.ItemRegistry;

public class VanillaAdditionsItemModelProvider extends ItemModelProvider {

    public VanillaAdditionsItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.TORCH_ARROW.get());
        basicItem(ItemRegistry.EXPLOSIVE_ARROW.get());
        basicItem(ItemRegistry.STEAK_SANDWHICH.get());
    }
}