package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class MeteorListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("Meteor")) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                projectile.getWorld().createExplosion(projectile.getLocation(), 3, true, false);
                projectile.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, projectile.getLocation(), 1,
                        0.2, 0.2, 0.2, 0.04);
            }, 1);
        }
    }

    @EventHandler
    public void onProjectileHit(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Projectile projectile)) {
            return;
        }

        if(projectile.hasMetadata("Meteor")) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                projectile.getWorld().createExplosion(projectile.getLocation(), 4.2f, true, true);
                projectile.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, projectile.getLocation(), 1,
                        0.2, 0.2, 0.2, 0.04);

                projectile.remove();
            }, 1);
        }
    }
}
