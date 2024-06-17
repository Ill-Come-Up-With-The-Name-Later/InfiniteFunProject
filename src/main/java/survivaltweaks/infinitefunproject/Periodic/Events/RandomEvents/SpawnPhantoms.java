package survivaltweaks.infinitefunproject.Periodic.Events.RandomEvents;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SpawnPhantoms implements RandomEvent {

    @Override
    public void trigger() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();

        for(Player player : players) {
            Location loc = player.getLocation();
            loc.add(0, 16, 0);
            player.sendMessage(ChatColor.DARK_BLUE + "Screeches can be heard from above... Watch out!");
            Phantom phantom = (Phantom) player.getWorld().spawnEntity(loc, EntityType.PHANTOM);
            phantom.setTarget(player);
        }
    }
}
