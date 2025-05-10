package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

import java.util.Random;

public class GiveSpecialItems implements RandomEvent {

    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(int i = 0; i < 5; i++) {
                player.getInventory().addItem(ItemManager.customItems.get(new Random().nextInt(0, ItemManager.customItems.size())));
            }

            player.sendMessage(ChatColor.LIGHT_PURPLE + "Hope these help!");
        }
    }
}
