package pvpconstrain.pvpconstrain;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config {

    private final HashMap<String, String> messages = new HashMap<>();
    private final List<String> events = new ArrayList<>();
    private final JavaPlugin core;
    private final List<String> swords = new ArrayList<>();
    private String anvil;
    private String brewery;
    private String enchant;

    Config(JavaPlugin core) {
        this.core = core;
        load();
    }

    private String initString(String string) {
        string = string
                .replace("&", "\u00a7")
                .replace("[", "")
                .replace("]", "");
        return string;
    }

    public void load() {
        core.reloadConfig();
        FileConfiguration cfg = core.getConfig();

        events.clear();
        messages.clear();
        swords.clear();

        String[] eventsCheck = {"block_anvil", "block_enchant", "block_brewery", "block_sword", "block_bow"};
        for (String path : eventsCheck) {
            if (cfg.getBoolean(path + ".enabled")) {
                events.add(path);
                messages.put(path, initString(cfg.getString(path + ".message")));
            }
        }

        List<String> list = cfg.getStringList("swords");
        for (String sword : list) {
            sword = sword.replace("[", "").replace("]", "");
            swords.add(sword);
        }

        anvil = cfg.getString("anvil");
        enchant = cfg.getString("enchant");
        brewery = cfg.getString("brewing");
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public boolean isEnabled(String event) {
        return !events.contains(event);
    }

    public List<String> getSwords() {
        return swords;
    }

    public String getAnvil() {
        return anvil;
    }

    public String getBrewery() {
        return brewery;
    }

    public String getEnchant() {
        return enchant;
    }
}
