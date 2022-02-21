package me.gleeming.tabey;

import lombok.Getter;
import lombok.Setter;
import me.gleeming.tabey.player.TabeyPlayer;
import me.gleeming.tabey.player.version.VersionHandler;
import me.gleeming.tabey.reflection.impl.*;
import me.gleeming.tabey.skin.Skin;
import me.gleeming.tabey.tab.TabAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Getter @Setter
public class Tabey implements Listener {

    @Getter private static Tabey instance;

    private final Executor tabeyThread = Executors.newSingleThreadExecutor();
    private final Plugin plugin;

    private final TabAdapter tabAdapter;
    private int updateInterval = 2;

    public Tabey(Plugin plugin, TabAdapter tabAdapter) {
        instance = this;

        this.plugin = plugin;
        this.tabAdapter = tabAdapter;

        new TabeyThread();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        boolean legacy = VersionHandler.getInstance().isLegacy(player);

        tabeyThread.execute(() -> {
            new RPacketTeam(0, new RScoreboardTeam("90", null, null, Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList()))).sendPacket(player);
            new TabeyPlayer(event.getPlayer());

            if(VersionHandler.getInstance().isNative17() && legacy) {
                Bukkit.getOnlinePlayers().forEach(otherPlayer -> {
                    new RPacketInfo("REMOVE_PLAYER", new RPlayerInfoData(
                            new RGameProfile(otherPlayer.getUniqueId(), otherPlayer.getName(), new Skin("", "")),
                            otherPlayer.getName(),
                            otherPlayer.getGameMode(),
                            0
                    )).sendPacket(player);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        new RPacketInfo("ADD_PLAYER", new RPlayerInfoData(
                                new RGameProfile(otherPlayer.getUniqueId(), otherPlayer.getName(), new Skin(otherPlayer)),
                                otherPlayer.getName(),
                                otherPlayer.getGameMode(),
                                0
                        )).sendPacket(player);
                    }, 1);
                });
            }

            Bukkit.getOnlinePlayers().forEach(otherPlayer -> {
                new RPacketTeam(3, new RScoreboardTeam("90", null, null, Collections.singletonList(player.getName())))
                        .sendPacket(otherPlayer);
            });
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        TabeyPlayer.getPlayers().remove(player.getUniqueId());
    }

}
