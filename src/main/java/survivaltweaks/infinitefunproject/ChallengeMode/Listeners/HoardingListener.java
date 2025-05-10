package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class HoardingListener implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Hoarder")) {
            event.setCancelled(true);
        }
    }
}
