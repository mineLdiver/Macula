package net.mine_diver.macula;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

public class Macula implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        if (FabricLoader.getInstance().isModLoaded("smoothbeta"))
            Mixins.addConfiguration("macula.compat.smoothbeta.mixins.json");
    }
}
