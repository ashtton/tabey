package me.gleeming.tabey.tab;

import lombok.Getter;
import me.gleeming.tabey.skin.Skin;
import org.bukkit.GameMode;

@Getter
public class TabElement {

    private String text = "";
    private Skin skin = Skin.LIGHT_GRAY;
    private GameMode gameMode = GameMode.SURVIVAL;
    private int ping = 0;
    private int slot = 0;
    private TabColumn column = TabColumn.LEFT;

    /**
     * Updates the text
     * @param text Text
     * @return Tab Element
     */
    public TabElement text(String text) {
        this.text = text;
        return this;
    }

    /**
     * Updates the skin
     * @param skin Skin
     * @return Tab Element
     */
    public TabElement skin(Skin skin) {
        this.skin = skin;
        return this;
    }

    /**
     * Updates the ping
     * @param ping Ping
     * @return Tab Element
     */
    public TabElement ping(int ping) {
        this.ping = ping;
        return this;
    }

    /**
     * Updates the slot
     * @param slot Slot
     * @return Tab Element
     */
    public TabElement slot(int slot) {
        this.slot = slot;
        return this;
    }

    /**
     * Updates the tab column
     * @param column Column
     * @return Tab Element
     */
    public TabElement column(TabColumn column) {
        this.column = column;
        return this;
    }


    /**
     * Updates the gamemode
     * @param gameMode Gamemode
     * @return Tab Element
     */
    public TabElement gamemode(GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }
}
