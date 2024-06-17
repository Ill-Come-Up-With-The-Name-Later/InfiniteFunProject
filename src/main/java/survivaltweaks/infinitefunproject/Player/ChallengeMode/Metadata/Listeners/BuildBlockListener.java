package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildBlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("BuildBlock")) {
            event.setCancelled(true);
            event.setBuild(false);
        }
    }
}
