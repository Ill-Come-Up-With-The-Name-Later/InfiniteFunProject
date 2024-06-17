package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class SpawnZombies implements RandomEvent {

    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            Location loc = player.getLocation();
            player.sendMessage(color("&2Zombie Infestation!"));
            player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
            player.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        }
    }
}
