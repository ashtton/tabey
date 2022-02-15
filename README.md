# Tabey
This is a lightweight tab library supporting the minecraft versions and clients from 1.7 - 1.17.
### Features
* Only sends update packets when changed which helps players with high latency
* Easily changeable skin by just providing player object, uuid, name, or skin signature.
* Editable latency (set to 0 to make it not display on most pvp clients)
### Command Example
This example shows you the basics of using the api
```java
// Package: me.gleeming.example
public class TabExample extends JavaPlugin {
    public void onEnable() {
        // Whenever your plugin enables, you need
        // to register a new provider by doing
        new Tabey(this, new TabProvider());
    }
}

// Package: me.gleeming.example.tab
// The only method you have to implement in this
// class is the display, although as you can see
// you can also provide tab headers and footers
public class TabProvider extends TabAdapter {
    
    @Override
    public List<String> getHeader(Player player) {
        return Arrays.asList(
                "",
                "&a&lHello header!",
                ""
        );
    }

    @Override
    public List<String> getFooter(Player player) {
        return Arrays.asList(
                "",
                "&c&lHello footer!",
                ""
        );
    }
    
    @Override
    public List<TabElement> getDisplay(Player player) {
        List<TabElement> elements = new ArrayList();
        
        // You can add however many elements
        // to this list as you want and can
        // only fill out the parameters you want
        
        elements.add(new TabElement()
                .column(TabColumn.LEFT)
                .slot(0)
                .skin(new Skin("Notch"))
                .ping(1000)
                .gamemode(GameMode.SPECTATOR)
                .text("&c&lHello " + player.getName())
        );
        
        return elements;
    }
}

```