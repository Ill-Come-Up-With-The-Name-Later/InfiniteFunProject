package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class IMeMineListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 10);

        if(player.hasMetadata("I Me Mine")) {
            for(Entity entity : entities) {
                if(entity instanceof Player) {
                    player.damage(Integer.MAX_VALUE);
                }
            }
        }
    }
}
