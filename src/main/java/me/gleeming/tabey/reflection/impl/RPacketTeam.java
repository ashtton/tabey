package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class RPacketTeam extends Reflection {

    private final int action;
    private final RScoreboardTeam scoreboardTeam;

    /**
     * Sends the packet to the player
     * @param player Player
     */
    public void sendPacket(Player player) {
        Object craftPlayerHandle = callMethod(player, "getHandle");
        Object playerConnection = getField(craftPlayerHandle, "playerConnection");

        if(action == 3 || action == 4) {
            callMethod(playerConnection, "sendPacket", initialize(
                    getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutScoreboardTeam"),
                    scoreboardTeam.create(player), scoreboardTeam.getPlayers(), action
            ));
            return;
        }
        callMethod(playerConnection, "sendPacket", initialize(
                getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutScoreboardTeam"),
                scoreboardTeam.create(player), action
        ));
    }
}
