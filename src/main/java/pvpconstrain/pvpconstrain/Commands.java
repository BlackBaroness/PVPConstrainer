package pvpconstrain.pvpconstrain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final Config config;

    Commands(Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("constrain.admin")) {
                p.sendMessage(ChatColor.AQUA + "[PVPConstrain] " + ChatColor.RED + "Вы не администратор.");
                return true;
            }
            if (args.length == 0) {
                pHelp(p);
                return true;
            }
            if (args.length > 1) {
                p.sendMessage(ChatColor.AQUA + "[PVPConstrain] " + ChatColor.RED + "Неизвестная субкоманда!");
                return true;
            }
            if (args[0].equals("reload")) {
                config.load();
                p.sendMessage(ChatColor.AQUA + "[PVPConstrain] " + ChatColor.WHITE + "Плагин успешно перезагружен.");
            } else {
                pHelp(p);
            }
            return true;
        } else {
            if (args.length == 0) {
                help();
                return true;
            }
            if (args.length > 1) {
                System.out.println(ChatColor.RED + "Invalid subcommand.");
                return true;
            }
            if (args[0].equals("reload")) {
                config.load();
                System.out.println(ChatColor.AQUA + "[PVPConstrain] " + ChatColor.WHITE + "Reloaded.");
            } else {
                help();
            }
        }
        return true;
    }

    private void pHelp(Player p) {
        p.sendMessage(ChatColor.DARK_GREEN + "/pvpc reload " + ChatColor.WHITE + "перезагружает плагин.");
    }

    private void help() {
        System.out.println(ChatColor.DARK_GREEN + "/pvpc reload " + ChatColor.WHITE + "reload the plugin.");
    }
}
