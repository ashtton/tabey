package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class RPacketHeaderFooter extends Reflection {

    private final List<String> headerList;
    private final List<String> footerList;

    /**
     * Sends the packet to the player
     * @param player Player
     */
    public void sendPacket(Player player) {
        StringBuilder headerBuilder = new StringBuilder();
        StringBuilder footerBuilder = new StringBuilder();

        headerList.forEach(line -> headerBuilder.append(ChatColor.translateAlternateColorCodes('&', line)).append("\n"));
        footerList.forEach(line -> footerBuilder.append(ChatColor.translateAlternateColorCodes('&', line)).append("\n"));

        Class<?> chatSerializer = getClass("net.minecraft.server." + NMS_VERSION + ".IChatBaseComponent$ChatSerializer");

        Object headerComponent = callMethod(chatSerializer, "a", "{\"text\":\"" + headerBuilder.substring(0, headerBuilder.toString().length() - 1) + "\"}");
        Object footerComponent = callMethod(chatSerializer, "a", "{\"text\":\"" + footerBuilder.substring(0, footerBuilder.toString().length() - 1) + "\"}");

        Object packet = initialize(getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutPlayerListHeaderFooter"));
        setDeclaredField(packet, "a", headerComponent);
        setDeclaredField(packet, "b", footerComponent);

        Object craftPlayerHandle = callMethod(player, "getHandle");
        Object playerConnection = getField(craftPlayerHandle, "playerConnection");

        callMethod(playerConnection, "sendPacket", packet);
    }
}
