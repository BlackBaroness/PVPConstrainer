package pvpconstrain.pvpconstrain;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class ProtectBlocks implements Listener {

    private final Config data;

    ProtectBlocks(Config data) {
        this.data = data;
    }

    @EventHandler
    private void all(PlayerInteractEvent e) {
        Action a = e.getAction();
        if (a != Action.RIGHT_CLICK_BLOCK) return;
        Player p = e.getPlayer();
        Material material = Objects.requireNonNull(e.getClickedBlock()).getType();

        if (block(p, "constrain.anvil", "block_anvil", material, data.getAnvil())) {
            e.setCancelled(true);
            return;
        }

        if (block(p, "constrain.enchant", "block_enchant", material, data.getEnchant())) {
            e.setCancelled(true);
            return;
        }

        if (block(p, "constrain.brewery", "block_brewery", material, data.getBrewery())) {
            e.setCancelled(true);
        }
    }

    private String initString(String string, String nick) {
        string = string.replace("{nick}", nick);
        return string;
    }

    private boolean block(Player p, String permission, String eventName, Material block, String blocked) {
        if (!block.toString().equals(blocked)) return false;
        if (data.isEnabled(eventName)) return false;
        if (p.hasPermission(permission)) return false;
        p.sendMessage(initString(data.getMessages().get(eventName), p.getName()));
        return true;
    }
}
