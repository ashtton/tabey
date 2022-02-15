package me.gleeming.tabey.player.version.impl;

import me.gleeming.tabey.player.version.VersionHandler;
import me.gleeming.tabey.reflection.impl.RPlayerVersion;
import org.bukkit.entity.Player;

public class VanillaHandler extends VersionHandler {
    @Override
    public boolean isLegacy(Player player) {
        return new RPlayerVersion(player).getVersion() < 47;
    }
}
