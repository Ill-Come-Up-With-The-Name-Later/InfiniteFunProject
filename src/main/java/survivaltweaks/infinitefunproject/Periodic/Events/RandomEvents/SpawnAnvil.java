package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Collection;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;

public class SpawnAnvil implements RandomEvent {
    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            player.sendMessage(color("&7&lWatch your head!"));
            Location loc = player.getLocation();
            loc.add(0, 10, 0);
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () ->
                    player.getWorld().spawnFallingBlock(loc, Material.ANVIL.createBlockData()), 10);
        }
    }
}
