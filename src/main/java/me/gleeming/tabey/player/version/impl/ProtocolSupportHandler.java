package me.gleeming.tabey.player.version.impl;

import me.gleeming.tabey.player.version.VersionHandler;
import org.bukkit.entity.Player;
import protocolsupport.api.ProtocolSupportAPI;

public class ProtocolSupportHandler extends VersionHandler {
    @Override
    public boolean isLegacy(Player player) {
        return ProtocolSupportAPI.getProtocolVersion(player).getId() < 47;
    }
}
