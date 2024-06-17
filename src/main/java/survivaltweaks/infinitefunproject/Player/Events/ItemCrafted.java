package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.Random;

public class ItemCrafted implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(new Random().nextInt(0, 100) <= 1) {
            player.getWorld().createExplosion(player.getLocation(), 2.7f, false, false);
            player.sendMessage(ChatColor.RED + "A crafting error has occurred!");
        }
    }
}
