package net.dragonMaster.sky_technology_utils.event;

import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.client.TestValueHudOverlay;
import net.dragonMaster.sky_technology_utils.networking.ModPackages;
import net.dragonMaster.sky_technology_utils.networking.packet.TestC2SPacket;
import net.dragonMaster.sky_technology_utils.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = SkyTechnologyUtils.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.TEST_KEY.consumeClick()) {
                ModPackages.sendToServer(new TestC2SPacket());
            }
        }
        @Mod.EventBusSubscriber(modid = SkyTechnologyUtils.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBus {
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyBinding.TEST_KEY);
            }
            @SubscribeEvent
            public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
                event.registerAboveAll("test_value", TestValueHudOverlay.HUD_TEST_VALUE);
            }
        }
    }
}
