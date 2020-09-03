package pvpconstrain.pvpconstrain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) saveDefaultConfig();

        Config configClass = new Config(this);
        ProtectBlocks protectBlocks = new ProtectBlocks(configClass);
        ProtectPVP protectPVP = new ProtectPVP(configClass);
        Commands commands = new Commands(configClass);

        Bukkit.getPluginManager().registerEvents(protectBlocks, this);
        Bukkit.getPluginManager().registerEvents(protectPVP, this);
        Objects.requireNonNull(getCommand("pvpc")).setExecutor(commands);
        getLogger().info(ChatColor.YELLOW + "Enabled!");
    }
}
