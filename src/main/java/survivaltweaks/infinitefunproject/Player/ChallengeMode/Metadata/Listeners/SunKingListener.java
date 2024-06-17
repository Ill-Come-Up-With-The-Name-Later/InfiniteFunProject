package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SunKingListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        int light = player.getLocation().getBlock().getLightLevel();

        if(light < 9 && player.hasMetadata("SunKing")) {
            player.damage(Integer.MAX_VALUE);
        }
    }
}
