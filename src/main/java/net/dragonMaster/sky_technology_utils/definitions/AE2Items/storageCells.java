package net.dragonMaster.sky_technology_utils.definitions.AE2Items;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import appeng.items.materials.StorageComponentItem;
import net.minecraft.world.item.Item;

import appeng.api.stacks.AEKeyType;
import appeng.core.definitions.ItemDefinition;
import appeng.items.materials.MaterialItem;
import appeng.items.storage.BasicStorageCell;
import appeng.items.storage.StorageTier;
import net.minecraft.resources.ResourceLocation;


import static net.dragonMaster.sky_technology_utils.SkyTechnologyUtils.MOD_ID;

public class storageCells {


    public static ResourceLocation makeId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
    private static StorageTier tier(int index, ItemDefinition<StorageComponentItem> component) {
        int multiplier = (int) Math.pow(4, index - 1);
        return new StorageTier(
                index, multiplier + "m", 1048576 * multiplier, 2.5 + 0.5 * multiplier, component::asItem);
    }
    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();

    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }
    public static final ItemDefinition<MaterialItem> ULTRA_ITEM_CELL_HOUSING =
            item("ULTRA Item Cell Housing", "ultra_item_cell_housing", MaterialItem::new);
    public static final ItemDefinition<MaterialItem> ULTRA_FLUID_CELL_HOUSING =
            item("ULTRA Fluid Cell Housing", "ultra_fluid_cell_housing", MaterialItem::new);
    private static ItemDefinition<StorageComponentItem> component(int mb) {
        return item(
                mb + "M MEGA Storage Component",
                "cell_component_" + mb + "m",
                p -> new StorageComponentItem(p, mb * 1024));
    }
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_1M = component(1);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_4M = component(4);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_16M = component(16);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_64M = component(64);
    public static final ItemDefinition<StorageComponentItem> CELL_COMPONENT_256M = component(256);
    public static final StorageTier TIER_1M = tier(1, CELL_COMPONENT_1M);
    public static final StorageTier TIER_4M = tier(2, CELL_COMPONENT_4M);
    public static final StorageTier TIER_16M = tier(3, CELL_COMPONENT_16M);
    public static final StorageTier TIER_64M = tier(4, CELL_COMPONENT_64M);
    public static final StorageTier TIER_256M = tier(5, CELL_COMPONENT_256M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_1M = itemCell(TIER_1M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_4M = itemCell(TIER_4M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_16M = itemCell(TIER_16M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_64M = itemCell(TIER_64M);
    public static final ItemDefinition<BasicStorageCell> ITEM_CELL_256M = itemCell(TIER_256M);

    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_1M = fluidCell(TIER_1M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_4M = fluidCell(TIER_4M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_16M = fluidCell(TIER_16M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_64M = fluidCell(TIER_64M);
    public static final ItemDefinition<BasicStorageCell> FLUID_CELL_256M = fluidCell(TIER_256M);
    public static List<ItemDefinition<?>> getItemCells() {
        return List.of(ITEM_CELL_1M, ITEM_CELL_4M, ITEM_CELL_16M, ITEM_CELL_64M, ITEM_CELL_256M);
    }

    public static List<ItemDefinition<?>> getFluidCells() {
        return List.of(FLUID_CELL_1M, FLUID_CELL_4M, FLUID_CELL_16M, FLUID_CELL_64M, FLUID_CELL_256M);
    }
    public static ItemDefinition<BasicStorageCell> itemCell(StorageTier tier) {
        return item(
                tier.namePrefix().toUpperCase() + " MEGA Item Storage Cell",
                "item_storage_cell_" + tier.namePrefix(),
                p -> new BasicStorageCell(
                        p.stacksTo(1),
                        tier.componentSupplier().get(),
                        ULTRA_ITEM_CELL_HOUSING,
                        tier.idleDrain(),
                        tier.bytes() / 1024,
                        tier.bytes() / 128,
                        63,
                        AEKeyType.items()));
    }

    public static ItemDefinition<BasicStorageCell> fluidCell(StorageTier tier) {
        return item(
                tier.namePrefix().toUpperCase() + " MEGA Fluid Storage Cell",
                "fluid_storage_cell_" + tier.namePrefix(),
                p -> new BasicStorageCell(
                        p.stacksTo(1),
                        tier.componentSupplier().get(),
                        ULTRA_FLUID_CELL_HOUSING,
                        tier.idleDrain(),
                        tier.bytes() / 1024,
                        tier.bytes() / 128,
                        9,
                        AEKeyType.fluids()));
    }


    public static <T extends Item> ItemDefinition<T> item(
            String englishName, String id, Function<Item.Properties, T> factory) {
        Item.Properties p = new Item.Properties();
        T item = factory.apply(p);

        ItemDefinition<T> definition = new ItemDefinition<>(englishName, makeId(id), item);
        ITEMS.add(definition);

        return definition;
    }

}
