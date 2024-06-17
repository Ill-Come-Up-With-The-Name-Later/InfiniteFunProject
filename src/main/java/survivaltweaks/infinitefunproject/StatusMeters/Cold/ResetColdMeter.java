package survivaltweaks.infinitefunproject.StatusMeters.Cold;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.*;

public class ResetColdMeter implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        setupCold(player);
    }

    @EventHandler
    public void onCatchFire(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();

        if(entity instanceof Player && (cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK)) {
            Player player = (Player) entity;

            setCold(player, Math.max(getCold(player) - 3, 0));
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player) {
            Player player = (Player) entity;
            setCold(player, 0);
        }
    }
}
