package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.gleeming.tabey.reflection.Reflection;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
@AllArgsConstructor
@Getter
public class RScoreboardTeam extends Reflection {

    private final String name;
    private final String prefix;
    private final String suffix;
    private final List<String> players;

    /**
     * Creates the scoreboard team
     * @param player Player
     * @return Scoreboard Team
     */
    public Object create(Player player) {
        Object craftPlayerHandle = callMethod(player, "getHandle");
        Object scoreboardTeam = initialize(
                getClass("net.minecraft.server." + NMS_VERSION + ".ScoreboardTeam"),
                callMethod(craftPlayerHandle, "getScoreboard"), name
        );

        if(players != null)
            ((Collection<String>) callMethod(scoreboardTeam, "getPlayerNameSet")).addAll(players);

        if(prefix != null)
            setDeclaredField(scoreboardTeam, "e", prefix);

        if(suffix != null)
            setDeclaredField(scoreboardTeam, "f", suffix);

        return scoreboardTeam;
    }
}
