package net.dragonMaster.sky_technology_utils.painting;

import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, SkyTechnologyUtils.MOD_ID);
    public static final RegistryObject<PaintingVariant> TEST_PAINTING = PAINTING_VARIANTS.register("test_painting",() -> new PaintingVariant(32,32));
    public static void register(IEventBus eventBus) {
        PAINTING_VARIANTS.register(eventBus);
    }
}
