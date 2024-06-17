package survivaltweaks.infinitefunproject.World.Infection;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class RemoveInfection implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        LivingEntity living = event.getEntity();

        if(living.hasMetadata("Infected")) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> living.removeMetadata("Infected", InfiniteFunProject.plugin), 2);
        }

        if(living.hasMetadata("Immune")) {
            living.removeMetadata("Immune", InfiniteFunProject.plugin);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(player.hasMetadata("Infected")) {
            player.removeMetadata("Infected", InfiniteFunProject.plugin);
        }

        if(player.hasMetadata("Immune")) {
            player.removeMetadata("Immune", InfiniteFunProject.plugin);
        }
    }
}
