package survivaltweaks.infinitefunproject.Champions.Blaze;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class FireballExplosion implements Listener {

    @EventHandler
    public void projectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Blaze) {
            Blaze blaze = (Blaze) projectile.getShooter();

            if(blaze.hasMetadata("Champion")) {
                projectile.getWorld().createExplosion(projectile.getLocation(), 1.3f, true, false, blaze);
            }
        }
    }
}
