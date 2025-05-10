package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SpawnSpiders implements RandomEvent {

    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            Location loc = player.getLocation();
            player.sendMessage(ChatColor.RED + "Sorry arachnophobes!");
            player.getWorld().spawnEntity(loc, EntityType.SPIDER);
            player.getWorld().spawnEntity(loc, EntityType.SPIDER);
        }
    }
}
