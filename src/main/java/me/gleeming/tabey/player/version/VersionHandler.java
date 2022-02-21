package me.gleeming.tabey.player.version;

import lombok.Getter;
import me.gleeming.tabey.player.version.impl.ProtocolSupportHandler;
import me.gleeming.tabey.player.version.impl.StrawHandler;
import me.gleeming.tabey.player.version.impl.VanillaHandler;
import me.gleeming.tabey.player.version.impl.ViaVersionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class VersionHandler {

    @Getter
    private static final VersionHandler instance = Bukkit.getPluginManager().getPlugin("ViaVersion") != null ? new ViaVersionHandler() :
            Bukkit.getPluginManager().getPlugin("ProtocolSupport") != null ? new ProtocolSupportHandler() :
                    Bukkit.getPluginManager().getPlugin("straw") != null ? new StrawHandler() :
                            new VanillaHandler();

    /**
     * Checks if a player is legacy
     * @param player Player
     * @return Legacy
     */
    public abstract boolean isLegacy(Player player);

    /**
     * Checks if 1.7 clients should be treated natively
     * @return Native 1.7
     */
    public abstract boolean isNative17();
}
