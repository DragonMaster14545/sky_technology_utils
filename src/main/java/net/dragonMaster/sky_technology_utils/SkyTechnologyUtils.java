package net.dragonMaster.sky_technology_utils;

import com.mojang.logging.LogUtils;
import net.dragonMaster.sky_technology_utils.blocks.ModBlocks;
import net.dragonMaster.sky_technology_utils.items.ModItems;
import net.dragonMaster.sky_technology_utils.painting.ModPaintings;
import net.dragonMaster.sky_technology_utils.villager.ModVillagers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(SkyTechnologyUtils.MOD_ID)
public class SkyTechnologyUtils {
    public static final String MOD_ID = "sky_technology_utils";
    private static final Logger LOGGER = LogUtils.getLogger();
    public SkyTechnologyUtils(){



        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModPaintings.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);



    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
        ModVillagers.registerPOIs();
    });
    }




    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TEST_CROP.get(), RenderType.cutout());
        }
    }
}
