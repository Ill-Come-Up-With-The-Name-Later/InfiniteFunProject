package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.Periodic.Events.Anomalies.MakePlayersInvincible.grantInvulnerability;

public class OnRespawn implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if(player.hasMetadata("Cancer")) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> player.removeMetadata("Cancer", InfiniteFunProject.plugin), 2);
        }

        if(player.hasMetadata("ProjShield")) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                    () -> player.removeMetadata("ProjShield", InfiniteFunProject.plugin), 2);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> grantInvulnerability(player, 100), 3);
    }
}
