package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.Random;

public class GiveRandomSpecialItem implements RandomEvent {
    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(ItemManager.customItems.get(new Random().nextInt(0, ItemManager.customItems.size())));
            player.sendMessage(ChatColor.GREEN + "Hope this helps.");
        }
    }
}
