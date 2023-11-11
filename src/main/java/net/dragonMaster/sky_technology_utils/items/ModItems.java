package net.dragonMaster.sky_technology_utils.items;

import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.blocks.ModBlocks;
import net.dragonMaster.sky_technology_utils.items.custom.DiceItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SkyTechnologyUtils.MOD_ID);

    public static final RegistryObject<Item> TestItem = ITEMS.register("test_item",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.SkyTechnologyUtilsTabMisc)));
    public static final RegistryObject<Item> TestDice = ITEMS.register("test_dice",() -> new DiceItem(new Item.Properties().tab(ModCreativeModeTab.SkyTechnologyUtilsTabMisc)));
    public static final RegistryObject<Item> TestSeeds = ITEMS.register("test_seeds",() -> new ItemNameBlockItem(ModBlocks.TEST_CROP.get(), new Item.Properties().tab(ModCreativeModeTab.SkyTechnologyUtilsTabMisc)));
    public static final RegistryObject<Item> TestFruit = ITEMS.register("test_fruit",() -> new Item(new Item.Properties().tab(ModCreativeModeTab.SkyTechnologyUtilsTabMisc).food(new FoodProperties.Builder().nutrition(2).saturationMod(2).build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
