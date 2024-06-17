package survivaltweaks.infinitefunproject.Periodic.Events.Anomalies;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents.RandomEvent;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class SpawnBlazes implements RandomEvent {

    @Override
    public void trigger() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            Location loc = player.getLocation();
            player.sendMessage(color("&6Blazes have crossed the dimensions!"));
            player.getWorld().spawnEntity(loc, EntityType.BLAZE);
            player.getWorld().spawnEntity(loc, EntityType.BLAZE);
        }
    }
}
