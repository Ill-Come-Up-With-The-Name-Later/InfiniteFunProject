package survivaltweaks.infinitefunproject.World.Infection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathMessage implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(player.hasMetadata("Infected")) {
            event.setDeathMessage(player.getDisplayName() + " died from Coronavirus");
        }
    }
}
