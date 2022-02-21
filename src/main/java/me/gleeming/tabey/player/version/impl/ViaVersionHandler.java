package me.gleeming.tabey.player.version.impl;

import com.viaversion.viaversion.api.Via;
import me.gleeming.tabey.player.version.VersionHandler;
import org.bukkit.entity.Player;

@SuppressWarnings("unchecked")
public class ViaVersionHandler extends VersionHandler {

    @Override
    public boolean isLegacy(Player player) {
        return Via.getAPI().getPlayerVersion(player) < 47;
    }

    @Override
    public boolean isNative17() {
        return false;
    }
}
