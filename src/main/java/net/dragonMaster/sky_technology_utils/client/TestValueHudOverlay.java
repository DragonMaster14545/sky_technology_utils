package net.dragonMaster.sky_technology_utils.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
public class TestValueHudOverlay {
    private static final ResourceLocation FILLED_TEST_VALUE = new ResourceLocation(SkyTechnologyUtils.MOD_ID,
            "textures/misc/filled_test_value.png");
    private static final ResourceLocation EMPTY_TEST_VALUE = new ResourceLocation(SkyTechnologyUtils.MOD_ID,
            "textures/misc/empty_test_value.png");

    public static final IGuiOverlay HUD_TEST_VALUE = ((gui, poseStack, partialTick, width, height) -> {
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_TEST_VALUE);
        for(int i = 0; i < 10; i++) {
            GuiComponent.blit(poseStack,x - 94 + (i * 9), y - 54,0,0,12,12,
                    12,12);
        }

        RenderSystem.setShaderTexture(0, FILLED_TEST_VALUE);
        for(int i = 0; i < 10; i++) {
            if(ClientTestValueData.getPlayerTestValueData() > i*100) {
                GuiComponent.blit(poseStack,x - 94 + (i * 9),y - 54,0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    });
}
