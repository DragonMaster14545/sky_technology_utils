package net.dragonMaster.sky_technology_utils;
import java.util.ArrayList;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import appeng.block.networking.EnergyCellBlockItem;
import appeng.client.gui.implementations.InterfaceScreen;
import appeng.client.gui.implementations.PatternProviderScreen;
import appeng.client.render.crafting.CraftingCubeModel;
import appeng.client.render.crafting.CraftingMonitorRenderer;
import appeng.core.AppEng;
import appeng.init.client.InitScreens;
import appeng.items.storage.BasicStorageCell;
import appeng.items.tools.powered.PortableCellItem;

import net.dragonMaster.sky_technology_utils.definitions.AE2Items.storageCells;
public class client {
    private static void initItemColors(RegisterColorHandlersEvent.Item event) {
        var cells = new ArrayList<>(storageCells.getItemCells());
        cells.addAll(storageCells.getFluidCells());
        event.register(BasicStorageCell::getColor, cells.toArray(new ItemLike[0]));
    }

    static void init() {

        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(client::initItemColors);
    }
}
