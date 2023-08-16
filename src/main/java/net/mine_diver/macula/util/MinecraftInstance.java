package net.mine_diver.macula.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

public class MinecraftInstance {
    public static Minecraft get() {
        //noinspection deprecation
        return (Minecraft) FabricLoader.getInstance().getGameInstance();
    }
}
