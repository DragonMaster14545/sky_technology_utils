package net.dragonMaster.sky_technology_utils.blocks;

import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.blocks.custom.MagicAltarLv1;
import net.dragonMaster.sky_technology_utils.blocks.custom.TestCrop;
import net.dragonMaster.sky_technology_utils.blocks.custom.TestLamp;
import net.dragonMaster.sky_technology_utils.blocks.custom.YeetBlock;
import net.dragonMaster.sky_technology_utils.items.ModCreativeModeTab;
import net.dragonMaster.sky_technology_utils.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SkyTechnologyUtils.MOD_ID);

    public static  final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.SkyTechnologyUtilsTabMisc);
    public static  final RegistryObject<Block> MAGIC_ALTAR_LV1 = registerBlock("magic_altar_lv1",() -> new MagicAltarLv1(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.SkyTechnologyUtilsTabMisc);
    public static  final RegistryObject<Block> TEST_YEET_BLOCK = registerBlock("test_yeet_block",() -> new YeetBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.SkyTechnologyUtilsTabMisc);
    public static  final RegistryObject<Block> TEST_LAMP = registerBlock("test_lamp",() -> new TestLamp(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(TestLamp.LIT) ? 15 : 0)), ModCreativeModeTab.SkyTechnologyUtilsTabMisc);
    public static  final RegistryObject<Block> TEST_CROP = BLOCKS.register("test_crop",() -> new TestCrop(BlockBehaviour.Properties.copy(Blocks.WHEAT)));



    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
