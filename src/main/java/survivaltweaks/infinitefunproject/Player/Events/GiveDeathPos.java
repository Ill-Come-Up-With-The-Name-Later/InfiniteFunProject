package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GiveDeathPos implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location location = player.getLocation();

        player.sendMessage(ChatColor.RED + "Death Position: \nx: " + (int)location.getX() + "\ny: " + (int)location.getY()
                + "\nz: " + (int)location.getZ() + "\nDimension: " + location.getWorld().getEnvironment());
    }
}
