package me.gleeming.tabey.player.version.impl;

import me.gleeming.tabey.player.version.VersionHandler;
import me.gleeming.tabey.reflection.Reflection;
import me.gleeming.tabey.reflection.impl.RPlayerVersion;
import org.bukkit.entity.Player;

public class VanillaHandler extends VersionHandler {
    @Override
    public boolean isLegacy(Player player) {
        return new RPlayerVersion(player).getVersion() < 47;
    }

    @Override
    public boolean isNative17() {
        return Reflection.NMS_VERSION.contains("v1_7");
    }

}
