package net.dragonMaster.sky_technology_utils.networking.packet;

import net.dragonMaster.sky_technology_utils.PlayerData.TestValueProvider;
import net.dragonMaster.sky_technology_utils.client.ClientTestValueData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class TestValueSyncS2CPacket {
    private final int testValue;
    public TestValueSyncS2CPacket(int testValue) {
    this.testValue = testValue;
    }
    public TestValueSyncS2CPacket(FriendlyByteBuf buf) {
        this.testValue = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
    buf.writeInt(testValue);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //This will be executed by the client
            ClientTestValueData.set(testValue);
            });
        return true;
    }
}
