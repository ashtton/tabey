package me.gleeming.tabey.reflection.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.gleeming.tabey.reflection.Reflection;
import me.gleeming.tabey.skin.Skin;

import java.util.UUID;

@AllArgsConstructor
@Getter @Setter
public class RGameProfile extends Reflection {

    private final UUID uuid;
    private final String name;

    private Skin skin;

    /**
     * Creates the game profile
     * @return Game Profile
     */
    public Object create() {
        Object gameProfile = initialize(
                getClass("com.mojang.authlib.GameProfile"),
                uuid, name
        );

        Object properties = callMethod(gameProfile, "getProperties");
        callMethod(properties, "put", "textures", Reflection.initialize(
                Reflection.getClass("com.mojang.authlib.properties.Property"),
                "textures", skin.getValue(), skin.getSignature()
        ));

        return gameProfile;
    }
}
