package me.gleeming.tabey.player;

import lombok.Getter;
import me.gleeming.tabey.reflection.impl.*;
import me.gleeming.tabey.skin.Skin;
import me.gleeming.tabey.tab.TabElement;
import me.gleeming.tabey.team.TeamHandler;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

@Getter
public class TabeyPlayer {
    @Getter private static final HashMap<UUID, TabeyPlayer> players = new HashMap<>();

    private final HashMap<Integer, RGameProfile> entries = new HashMap<>();
    private final HashMap<Integer, TabElement> previousElements = new HashMap<>();

    public TabeyPlayer(Player player) {
        for (int i = 0; i < 81; i++) {
            RGameProfile gameProfile = new RGameProfile(UUID.randomUUID(), TeamHandler.getSlotNames().get(i), Skin.LIGHT_GRAY);
            entries.put(i, gameProfile);

            new RPacketInfo("ADD_PLAYER", new RPlayerInfoData(
                    gameProfile,
                    gameProfile.getName(),
                    GameMode.CREATIVE,
                    0
            )).sendPacket(player);

            new RPacketTeam(0, new RScoreboardTeam((i < 10 ? "0" : "") + i, "", "", Collections.singletonList(gameProfile.getName())))
                    .sendPacket(player);
        }

        players.put(player.getUniqueId(), this);
    }

    /**
     * Adds a new entry to the tab-list
     * @param data Data
     */
    public void refresh(Player player, RPlayerInfoData data, Skin skin) {
        new RPacketInfo("REMOVE_PLAYER", data).sendPacket(player);
        data.getGameProfile().setSkin(skin);
        new RPacketInfo("ADD_PLAYER", data).sendPacket(player);
    }


}
