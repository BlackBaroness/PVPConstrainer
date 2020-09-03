package pvpconstrain.pvpconstrain;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

public class ProtectPVP implements Listener {

    private final Config config;

    ProtectPVP(Config config) {
        this.config = config;
    }

    @EventHandler
    void blockSword(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (config.isEnabled("block_sword")) return;
        Player p = (Player) e.getDamager();
        if (p.hasPermission("constrain.sword")) return;
        String weapon = p.getInventory().getItemInMainHand().getType().toString();
        for (String string : config.getSwords()) {
            if (string.equals(weapon)) {
                e.setCancelled(true);
                p.sendMessage(initString(config.getMessages().get("block_sword"), p.getName()));
                return;
            }
        }
    }

    @EventHandler
    void blockBow(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (config.isEnabled("block_bow")) return;
        if (p.hasPermission("constrain.bow")) return;
        e.setCancelled(true);
        p.sendMessage(initString(config.getMessages().get("block_bow"), p.getName()));
    }

    private String initString(String string, String nick) {
        string = string.replace("{nick}", nick);
        return string;
    }
}
