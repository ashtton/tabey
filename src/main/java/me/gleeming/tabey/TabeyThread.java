package me.gleeming.tabey;

import lombok.SneakyThrows;
import me.gleeming.tabey.player.TabeyPlayer;
import me.gleeming.tabey.player.version.VersionHandler;
import me.gleeming.tabey.reflection.impl.*;
import me.gleeming.tabey.tab.TabElement;
import me.gleeming.tabey.team.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TabeyThread {

    public TabeyThread() {
        new Thread("tabey-thread") {
            @SneakyThrows
            public void run() {
                while(true) {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        try { tick(player); }
                        catch(Exception exception) {
                            exception.printStackTrace();
                            System.out.println("[Tabey] There was an exception whilst updating " + player.getName() + "'s tab.");
                        }
                    });

                    Thread.sleep(Tabey.getInstance().getUpdateInterval() * 50L);
                }
            }
        }.start();
    }

    public void tick(Player player) {
        HashMap<Integer, TabElement> elements = new HashMap<>();
        boolean legacy = VersionHandler.getInstance().isLegacy(player);

        if (legacy) Tabey.getInstance().getTabAdapter().getLegacy(player).forEach(tabElement -> elements.put(
                tabElement.getSlot() * 3 + tabElement.getColumn().getIndex(), tabElement
        ));
        else Tabey.getInstance().getTabAdapter().getDisplay(player).forEach(tabElement -> elements.put(
                tabElement.getColumn().getSlot() + tabElement.getSlot(), tabElement
        ));

        TabeyPlayer tabeyPlayer = TabeyPlayer.getPlayers().get(player.getUniqueId());
        tabeyPlayer.getEntries().forEach((i, gameProfile) -> {
            TabElement tabElement = elements.getOrDefault(i, new TabElement());
            RPlayerInfoData data = new RPlayerInfoData(
                    gameProfile,
                    ChatColor.translateAlternateColorCodes('&', tabElement.getText()),
                    tabElement.getGameMode(),
                    tabElement.getPing()
            );

            TabElement previousElement = tabeyPlayer.getPreviousElements().getOrDefault(i, new TabElement());
            if(previousElement.getPing() != tabElement.getPing()) new RPacketInfo("UPDATE_LATENCY", data).sendPacket(player);
            tabeyPlayer.getPreviousElements().put(i, tabElement);

            if(!legacy) {
                if(!tabElement.getSkin().matches(previousElement.getSkin()) || !tabElement.getGameMode().equals(previousElement.getGameMode())) {
                    tabeyPlayer.refresh(player, data, tabElement.getSkin());
                }

                if(!tabElement.getText().equals(previousElement.getText())) {
                    new RPacketInfo("UPDATE_DISPLAY_NAME", data).sendPacket(player);
                }

                return;
            }

            if(!tabElement.getText().equals(previousElement.getText())) {
                Map.Entry<String, String> entry = TeamHandler.splitString(tabElement.getText());
                new RPacketTeam(2, new RScoreboardTeam((i < 10 ? "0" : "") + i, entry.getKey(), entry.getValue(), Collections.singletonList(gameProfile.getName())))
                        .sendPacket(player);
            }
        });


        if(!legacy) {
            new RPacketHeaderFooter(
                    Tabey.getInstance().getTabAdapter().getHeader(player),
                    Tabey.getInstance().getTabAdapter().getFooter(player)
            ).sendPacket(player);
        }
    }


}
