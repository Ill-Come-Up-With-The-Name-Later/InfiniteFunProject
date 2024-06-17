package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ProjectileHits implements Listener {

    @EventHandler
    public void onShot(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damaged instanceof Player && damager instanceof Projectile) {
            Player player = (Player) damaged;
            player.setNoDamageTicks(0);
        }
    }
}
