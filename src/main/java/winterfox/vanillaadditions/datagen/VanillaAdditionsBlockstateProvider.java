package winterfox.vanillaadditions.datagen;

import net.minecraft.client.model.Model;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import winterfox.vanillaadditions.registry.BlockRegistry;

public class VanillaAdditionsBlockstateProvider extends BlockStateProvider {
    public VanillaAdditionsBlockstateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleStateBlockItemModel(BlockRegistry.EXPERIENCE_ORE.get());
        simpleStateBlockItemModel(BlockRegistry.DEEPSLATE_EXPERIENCE_ORE.get());

        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_ACACIA.get(), Blocks.ACACIA_PLANKS, Blocks.ACACIA_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_BIRCH.get(), Blocks.BIRCH_PLANKS, Blocks.BIRCH_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_CRIMSON.get(), Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_JUNGLE.get(), Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_OAK.get(), Blocks.OAK_PLANKS, Blocks.OAK_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_DARK_OAK.get(), Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_MANGROVE.get(), Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_SPRUCE.get(), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_FENCE);
        fenceStateBlockItemModel((FenceBlock) BlockRegistry.ONLYFENCE_WARPED.get(), Blocks.WARPED_PLANKS, Blocks.WARPED_FENCE);
    }

    private void simpleStateBlockItemModel(Block block) {
        simpleBlock(block);
        simpleBlockItem(block, models().getExistingFile(blockTexture(block)));
    }

    private void fenceStateBlockItemModel(FenceBlock block, Block texture, Block fence) {
        fenceBlock((FenceBlock) block, blockTexture(texture));
        //simpleBlockItem(block, parent);
        simpleBlockItem(block, blockTexture(fence).getPath()+"_inventory");
    }

    private void simpleBlockItem(Block block, String location) {
        ModelFile model = new ModelFile(new ResourceLocation(location)) {
            @Override
            protected boolean exists() {
                return true;
            }
        };
        simpleBlockItem(block, model);
    }
}