package me.gleeming.tabey.team;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TeamHandler {

    @Getter private static final HashMap<Integer, String> slotNames = new HashMap<>();

    static {
        AtomicInteger slot = new AtomicInteger(0);
        for(ChatColor firstColor : ChatColor.values()) for(ChatColor secondColor : ChatColor.values()) for(ChatColor thirdColor : ChatColor.values())
                    slotNames.put(slot.getAndIncrement(), firstColor.toString() + secondColor.toString() + thirdColor);
    }

    /**
     * Splits a string into a prefix and suffix
     * @return Prefix and Suffix
     */
    public static Map.Entry<String, String> splitString(String string) {
        String prefix = string, suffix = "";
        string = ChatColor.translateAlternateColorCodes('&', string);

        if(string.length() > 16) {
            int splitAt = string.charAt(15) == ChatColor.COLOR_CHAR ? 15 : 16;
            prefix = string.substring(0, splitAt);
            suffix = ChatColor.getLastColors(prefix) + string.substring(splitAt);
            suffix = suffix.substring(0, Math.min(suffix.length(), 16));
        }

        return new AbstractMap.SimpleEntry<>(prefix, suffix);
    }


}
