package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

@AllArgsConstructor
public class RPacketInfo extends Reflection {

    private String action;
    private RPlayerInfoData data;

    /**
     * Sends the packet to the player
     * @param player Player
     */
    public void sendPacket(Player player) {
        Object craftPlayerHandle = callMethod(player, "getHandle");
        Object playerConnection = getField(craftPlayerHandle, "playerConnection");
        Class<?> enumAction = getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutPlayerInfo$EnumPlayerInfoAction");

        Object packet = initialize(
                getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutPlayerInfo")
        );

        setDeclaredField(packet, "a", callMethod(enumAction, "valueOf", action));
        setDeclaredField(packet, "b", new ArrayList<>(Collections.singletonList(data.create(packet))));

        callMethod(playerConnection, "sendPacket", packet);
    }


}
