package net.dragonMaster.sky_technology_utils.networking.packet;

import net.dragonMaster.sky_technology_utils.PlayerData.TestValueProvider;
import net.dragonMaster.sky_technology_utils.networking.ModPackages;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TestC2SPacket {
    public TestC2SPacket() {

    }
    public TestC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //This will be executed by the server
            ServerPlayer player = context.getSender();
            ServerLevel level = context.getSender().getLevel();

            player.getCapability(TestValueProvider.PLAYER_TEST_VALUE).ifPresent(testValue -> {
                testValue.addTestValue(1);

            player.sendSystemMessage(Component.literal("Current test value: " + testValue.getTestValue()).withStyle(ChatFormatting.AQUA));
                ModPackages.sendToPlayer(new TestValueSyncS2CPacket(testValue.getTestValue()),player);
            });
            });
        return true;
    }
}
