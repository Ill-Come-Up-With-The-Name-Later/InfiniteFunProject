package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.facingSameWay;

public class BackstabListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;

            if(projectile.hasMetadata("Backstab")) {
                if(facingSameWay(projectile, damaged)) {
                    damaged.getWorld().spawnParticle(Particle.CRIT, damaged.getLocation().add(0, 0.7, 0),
                            10, 0.1, 0.2, 0.1, 0.03);
                    damaged.getWorld().spawnParticle(Particle.DAMAGE_INDICATOR, damaged.getLocation().add(0, 0.7, 0),
                            10, 0.1, 0.2, 0.1, 0.03);
                    drawCircle(damaged.getLocation().add(0, 0.7, 0), 2, Particle.FALLING_LAVA, 90);
                    event.setDamage(event.getDamage() * 1.5);
                }
            }
        }
    }
}
