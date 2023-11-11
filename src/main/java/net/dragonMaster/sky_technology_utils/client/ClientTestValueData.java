package net.dragonMaster.sky_technology_utils.client;

public class ClientTestValueData {
    private static int playerTestValueData;

    public static void set(int testValue) {
        ClientTestValueData.playerTestValueData = testValue;
    }

    public static int getPlayerTestValueData() {
        return playerTestValueData;
    }
}
