package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class TogetherListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Come Together")) {
            ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(player, 25);

            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    return;
                }
            }

            player.damage(Integer.MAX_VALUE);
        }
    }
}
