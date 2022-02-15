package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class RPlayerVersion extends Reflection {

    private final Player player;

    /**
     * Gets the players version
     * @return Player Version
     */
    public int getVersion() {
        if(!NMS_VERSION.startsWith("v1_7")) {
            return 47;
        }

        Object craftPlayerHandle = callMethod(player, "getHandle");
        Object playerConnection = getField(craftPlayerHandle, "playerConnection");
        Object networkManager = getField(playerConnection, "networkManager");

        return (int) callMethod(networkManager, "getVersion");
    }

}
