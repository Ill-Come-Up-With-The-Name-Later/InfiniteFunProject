package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LightDependentListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Light Dependent")) {
            int light = player.getLocation().getBlock().getLightLevel();

            player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(light + 5);
        }
    }
}
