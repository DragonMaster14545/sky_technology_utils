package net.dragonMaster.sky_technology_utils.villager;

import com.google.common.collect.ImmutableSet;
import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.blocks.ModBlocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, SkyTechnologyUtils.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,SkyTechnologyUtils.MOD_ID);
    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
    public static final RegistryObject<PoiType> TEST_BLOCK_POI = POI_TYPES.register("test_block_poi",() -> new PoiType(ImmutableSet.copyOf(ModBlocks.TEST_BLOCK.get().getStateDefinition().getPossibleStates()),1,1));
    public static final RegistryObject<VillagerProfession> TEST_TRADER = VILLAGER_PROFESSIONS.register("test_trader",() -> new VillagerProfession("test_trader",x -> x.get() == TEST_BLOCK_POI.get(),x -> x.get() == TEST_BLOCK_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,"registerBlockStates",PoiType.class).invoke(null,TEST_BLOCK_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exeption) {
            exeption.printStackTrace();
        }
    }

}
