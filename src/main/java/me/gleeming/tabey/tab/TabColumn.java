package me.gleeming.tabey.tab;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TabColumn {

    LEFT(0, 0), MIDDLE(1, 20), RIGHT(2, 40), FAR_RIGHT(3, 60);

    private final int index;
    private final int slot;

}
