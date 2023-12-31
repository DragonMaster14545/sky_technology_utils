package net.dragonMaster.sky_technology_utils.networking;

import net.dragonMaster.sky_technology_utils.SkyTechnologyUtils;
import net.dragonMaster.sky_technology_utils.networking.packet.TestC2SPacket;
import net.dragonMaster.sky_technology_utils.networking.packet.TestValueSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId ++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(SkyTechnologyUtils.MOD_ID,"packages")).networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;
        net.messageBuilder(TestC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER).decoder(TestC2SPacket::new).encoder(TestC2SPacket::toBytes).consumerMainThread(TestC2SPacket::handle).add();
        net.messageBuilder(TestValueSyncS2CPacket.class,id(), NetworkDirection.PLAY_TO_CLIENT).decoder(TestValueSyncS2CPacket::new).encoder(TestValueSyncS2CPacket::toBytes).consumerMainThread(TestValueSyncS2CPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
