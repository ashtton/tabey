package me.gleeming.tabey.skin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.gleeming.tabey.reflection.impl.RSkinFetcher;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Skin {

    private final String value;
    private final String signature;

    public Skin(Player player) {
        Map.Entry<String, String> entry = new RSkinFetcher(player).getSkin();
        this.value = entry.getKey();
        this.signature = entry.getValue();
    }

    public Skin(UUID uuid) {
        Map.Entry<String, String> entry = MojangAPI.getSkin(uuid);
        this.value = entry.getKey();
        this.signature = entry.getValue();
    }

    public Skin(String name) {
        Map.Entry<String, String> entry = MojangAPI.getSkin(name);
        this.value = entry.getKey();
        this.signature = entry.getValue();
    }

    /**
     * Checks if another skin matches this one
     * @param skin Skin
     * @return Matches
     */
    public boolean matches(Skin skin) {
        return value.equals(skin.getValue()) && signature.equals(skin.getSignature());
    }

    public static final Skin LIGHT_GRAY = new Skin(
            "eyJ0aW1lc3RhbXAiOjE0MTEyNjg3OTI3NjUsInByb2ZpbGVJZCI6IjNmYmVjN2RkMGE1ZjQwYmY5ZDExODg1YTU0NTA3MTEyIiwicHJvZmlsZU5hbWUiOiJsYXN0X3VzZXJuYW1lIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzg0N2I1Mjc5OTg0NjUxNTRhZDZjMjM4YTFlM2MyZGQzZTMyOTY1MzUyZTNhNjRmMzZlMTZhOTQwNWFiOCJ9fX0=",
            "u8sG8tlbmiekrfAdQjy4nXIcCfNdnUZzXSx9BE1X5K27NiUvE1dDNIeBBSPdZzQG1kHGijuokuHPdNi/KXHZkQM7OJ4aCu5JiUoOY28uz3wZhW4D+KG3dH4ei5ww2KwvjcqVL7LFKfr/ONU5Hvi7MIIty1eKpoGDYpWj3WjnbN4ye5Zo88I2ZEkP1wBw2eDDN4P3YEDYTumQndcbXFPuRRTntoGdZq3N5EBKfDZxlw4L3pgkcSLU5rWkd5UH4ZUOHAP/VaJ04mpFLsFXzzdU4xNZ5fthCwxwVBNLtHRWO26k/qcVBzvEXtKGFJmxfLGCzXScET/OjUBak/JEkkRG2m+kpmBMgFRNtjyZgQ1w08U6HHnLTiAiio3JswPlW5v56pGWRHQT5XWSkfnrXDalxtSmPnB5LmacpIImKgL8V9wLnWvBzI7SHjlyQbbgd+kUOkLlu7+717ySDEJwsFJekfuR6N/rpcYgNZYrxDwe4w57uDPlwNL6cJPfNUHV7WEbIU1pMgxsxaXe8WSvV87qLsR7H06xocl2C0JFfe2jZR4Zh3k9xzEnfCeFKBgGb4lrOWBu1eDWYgtKV67M2Y+B3W5pjuAjwAxn0waODtEn/3jKPbc/sxbPvljUCw65X+ok0UUN1eOwXV5l2EGzn05t3Yhwq19/GxARg63ISGE8CKw="
    );

    public static final Skin RED = new Skin(
            "ewogICJ0aW1lc3RhbXAiIDogMTYxNTMxNTk5ODIwMiwKICAicHJvZmlsZUlkIiA6ICIyNzc1MmQ2ZTUyYmM0MzVjYmNhOWQ5NzY1MjQ2YWNhNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZW1pbWVkIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2U4NzM3MWE2YzA3NmU2YmZlMjRmZjYwMGE3YTkzNjRkNTJiOTQ1N2E2YmQyODhjZWRlNmUzYjM1ZjFkNjJmNTciLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
            "u/WkOgfNmrKDhQHd8ZUEXGP2T5dWY9aW80slkclthButy01+I3V1WPvhevju6MSRuCPIXGSoChL4CtwUfLLOy7Oa9EgjuiysOAFQ1StiPeovyauLF6KTHGExY5Q/aPBqt95xspu4D4iiBMolpOFDUUO134dkaw28WQZ4qM3KhcEUVLEZoRxg0DgQKNNEptZ5ymWrtMBjmqfrMLOYEvw9mhuwzxOSx1MiWfINVIWNOpgFjRFuznRiPCYl81Tn5eBJbUfGWlwUoq+tEi6OpGwXi7McfYfnep10T/cGItAbUeoSc+v5aIrV1zf10Mg0d8Kxrf4a/76u0e16yJHhwfGqaL2ama6IUZRjBxYLGRZr5iC7o+619HNCaJTfH2EZSvu3bqddqdEy8gby5YR2xO9A02CitBw1mMqpuUVQbdfphnPWUBuGzwNcr8jIPkQhYUE4UdLxd3LpsIqZ6eRhvhi4MFDxTQsHd2j6L4cNz3eTRfmomL2xfHvREL55l49u8GPn249CQJNg+1ttgeAbyVjCHwxswoqjzAbZagr5RTciCA4iUibdYa+V0/saXsMJQPOobgTjBVo/+2Pbz0RtT6Ac2XdnQiL/NH0C4fajN8+PiWbb0BIhcU9kpGNfhBjEGVqFQOKS6ppARX/jtf9kZaBJAdRa5ggjDjxjpdfXKREyU8k="
    );

}
