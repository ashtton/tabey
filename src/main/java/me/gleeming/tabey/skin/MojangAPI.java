package me.gleeming.tabey.skin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MojangAPI {
    private static final HashMap<String, UUID> cachedUUID = new HashMap<>();
    private static final HashMap<UUID, Map.Entry<String, String>> cachedSkins = new HashMap<>();

    private static final Gson gson = new GsonBuilder().create();

    /**
     * Retrieves a players uuid from mojang api
     *
     * @param name Player Name
     * @return Fetched Name
     */
    @SneakyThrows
    public static UUID getUUID(String name) {
        if(cachedUUID.containsKey(name)) return cachedUUID.get(name);

        HttpURLConnection con = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        UUID uuid = UUID.fromString(gson.fromJson(response.toString(), JsonObject.class).get("id").getAsString());
        cachedUUID.put(name, uuid);
        return uuid;
    }

    /**
     * Fetches a skin by UUID from mojang
     *
     * @param uuid Player Unique ID
     * @return Fetched Skin
     */
    @SneakyThrows
    public static Map.Entry<String, String> getSkin(UUID uuid) {
        if(cachedSkins.containsKey(uuid)) return cachedSkins.get(uuid);

        HttpURLConnection con = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString() + "?unsigned=false").openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) response.append(inputLine);
        in.close();

        JsonObject profileDocument = gson.fromJson(response.toString(), JsonObject.class);
        JsonObject propertyDocument = (profileDocument.get("properties").getAsJsonArray().get(0)).getAsJsonObject();

        Map.Entry<String, String>  fetchedSkin = new AbstractMap.SimpleEntry<>(propertyDocument.get("value").getAsString(), propertyDocument.get("signature").getAsString());
        cachedSkins.put(uuid, fetchedSkin);
        return fetchedSkin;
    }

    /**
     * Fetches a skin by Name from mojang
     *
     * @param name Player Name
     * @return Fetched Skin
     */
    public static Map.Entry<String, String>  getSkin(String name) {
        return getSkin(getUUID(name));
    }
}