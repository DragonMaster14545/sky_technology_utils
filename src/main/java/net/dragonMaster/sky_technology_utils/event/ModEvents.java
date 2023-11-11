package net.dragonMaster.sky_technology_utils.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dragonMaster.sky_technology_utils.PlayerData.TestValue;
import net.dragonMaster.sky_technology_utils.PlayerData.TestValueProvider;
import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.items.ModItems;
import net.dragonMaster.sky_technology_utils.networking.ModPackages;
import net.dragonMaster.sky_technology_utils.networking.packet.TestValueSyncS2CPacket;
import net.dragonMaster.sky_technology_utils.villager.ModVillagers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = SkyTechnologyUtils.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.TEST_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.TestFruit.get(), 15);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
            //maxUses,VillagerExp,PriceMultiplier
        }

}

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(TestValueProvider.PLAYER_TEST_VALUE).isPresent()) {
                event.addCapability(new ResourceLocation(SkyTechnologyUtils.MOD_ID, "properties"), new TestValueProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(TestValueProvider.PLAYER_TEST_VALUE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(TestValueProvider.PLAYER_TEST_VALUE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(TestValue.class);
    }
    @SubscribeEvent
    public static void onPlayerJoinedWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(TestValueProvider.PLAYER_TEST_VALUE).ifPresent(testValue -> {
                    ModPackages.sendToPlayer(new TestValueSyncS2CPacket(testValue.getTestValue()),player);
                });
            }
        }
    }

}
