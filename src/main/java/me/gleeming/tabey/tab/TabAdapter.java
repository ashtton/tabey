package me.gleeming.tabey.tab;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TabAdapter {

    /**
     * Gets the tab header for the player
     * @param player Player
     * @return Tab Header
     */
    public List<String> getHeader(Player player) {
        return new ArrayList<>();
    }

    /**
     * Gets the tab footer for the player
     * @param player Player
     * @return Tab Footer
     */
    public List<String> getFooter(Player player) {
        return new ArrayList<>();
    }

    /**
     * Gets the tab sections for the player
     * @param player Player
     * @return Tab Sections
     */
    public abstract List<TabElement> getDisplay(Player player);

    /**
     * Gets a players elements
     * @param player Player
     * @return Elements
     */
    public List<TabElement> getLegacy(Player player) {
        return getDisplay(player).stream().filter(element -> element.getColumn() != TabColumn.FAR_RIGHT).collect(Collectors.toList());
    }
}
