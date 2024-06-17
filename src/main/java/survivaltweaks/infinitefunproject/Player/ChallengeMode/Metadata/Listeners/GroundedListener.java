package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GroundedListener implements Listener {

    @EventHandler
    public void onJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Grounded")) {
            event.setCancelled(true);
        }
    }
}
