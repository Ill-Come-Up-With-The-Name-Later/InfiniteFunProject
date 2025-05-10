package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.BounceProjectiles;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Random;

public class BounceArrowListener implements Listener {

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(projectile.hasMetadata("Bouncy")) {
                BounceProjectiles meta = (BounceProjectiles) projectile.getMetadata("Bouncy").get(0);

                if(projectile instanceof AbstractArrow) {
                    AbstractArrow arrow = (AbstractArrow) projectile;

                    if(arrow.getPierceLevel() < meta.getMaxBounces()) {
                        arrow.setPierceLevel(meta.getMaxBounces());
                    }
                }

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(!projectile.isDead()) {
                        projectile.remove();
                    }
                }, meta.getLifeSpan() - 2);
            }
        }, 2);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("Bouncy")) {
            BounceProjectiles meta = (BounceProjectiles) projectile.getMetadata("Bouncy").get(0);

            if(event.getHitEntity() != null) {
                event.setCancelled(true);
                projectile.setVelocity(projectile.getVelocity().multiply(new Random().nextDouble(-0.33, -0.2)));
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(event.getHitBlock() != null || projectile.isOnGround()) {
                    event.setCancelled(true);
                    Vector velocity = projectile.getVelocity().multiply(new Random().nextDouble(-1, -0.75));
                    velocity.multiply(17);

                    projectile.teleport(projectile.getLocation().add(0, 0.2, 0));
                    projectile.setVelocity(velocity);
                }
            }, 1);

            meta.setBounces(meta.getBounces() + 1);

            if(meta.getBounces() >= meta.getMaxBounces()) {
                projectile.remove();
            }
        }
    }
}
