package me.gleeming.tabey.player.version.impl;

import me.gleeming.tabey.player.version.VersionHandler;
import net.athenstudios.straw.api.StrawAPI;
import org.bukkit.entity.Player;

public class StrawHandler extends VersionHandler {

    @Override
    public boolean isLegacy(Player player) {
        return StrawAPI.isLegacy(player);
    }

    @Override
    public boolean isNative17() {
        return true;
    }
}
