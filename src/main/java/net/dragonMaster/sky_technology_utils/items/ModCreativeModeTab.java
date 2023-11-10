package net.dragonMaster.sky_technology_utils.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab SkyTechnologyUtilsTabMisc = new CreativeModeTab("SkyTechnologyUtilsTabMisc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TestItem.get());
        }
    };
}
