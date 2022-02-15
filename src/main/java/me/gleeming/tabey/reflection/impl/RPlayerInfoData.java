package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.GameMode;

@AllArgsConstructor
@Getter
public class RPlayerInfoData extends Reflection {

    private final RGameProfile gameProfile;
    private final String displayName;
    private final GameMode gameMode;
    private final int ping;

    /**
     * Creates the player info data
     * @return Player Info Data
     */
    public Object create(Object packet) {
        Class<?> gamemodeEnum = getClass("net.minecraft.server." + NMS_VERSION + ".WorldSettings$EnumGamemode");
        return initialize(
                getClass("net.minecraft.server." + NMS_VERSION + ".PacketPlayOutPlayerInfo$PlayerInfoData"),
                packet, gameProfile.create(), ping, callMethod(gamemodeEnum, "valueOf", gameMode.name()),
                initialize(getClass("net.minecraft.server." + NMS_VERSION + ".ChatMessage"), displayName, new Object[]{})
        );
    }
}
