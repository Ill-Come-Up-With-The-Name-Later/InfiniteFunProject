package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.List;

public class ExplosiveProjectiles implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof LivingEntity) {
            LivingEntity shooter = (LivingEntity) projectile.getShooter();

            if(projectile.hasMetadata("Explosive")) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    List<MetadataValue> explosiveMeta = projectile.getMetadata("Explosive");
                    ExplosiveMeta meta = (ExplosiveMeta) explosiveMeta.get(0);

                    projectile.getWorld().createExplosion(projectile.getLocation(), meta.getExplosionPower(), meta.isCauseFire(),
                            meta.isDestroyBlocks(), shooter);
                    projectile.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, projectile.getLocation(), 1,
                            0.2, 0.2, 0.2, 0.04);

                    if(projectile instanceof AbstractArrow) {
                        AbstractArrow arrow = (AbstractArrow) projectile;

                        if(arrow.getPierceLevel() < 1) {
                            arrow.remove();
                        }
                    }
                }, 1);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(EntityDamageByEntityEvent event) {
        if(!(event.getDamager() instanceof Projectile)) {
            return;
        }

        Projectile projectile = (Projectile) event.getDamager();

        if(projectile.getShooter() instanceof LivingEntity) {
            LivingEntity shooter = (LivingEntity) projectile.getShooter();

            if(projectile.hasMetadata("Explosive")) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    List<MetadataValue> explosiveMeta = projectile.getMetadata("Explosive");
                    ExplosiveMeta meta = (ExplosiveMeta) explosiveMeta.get(0);

                    projectile.getWorld().createExplosion(projectile.getLocation(), meta.getExplosionPower(), meta.isCauseFire(),
                            meta.isDestroyBlocks(), shooter);
                    projectile.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, projectile.getLocation(), 1,
                            0.2, 0.2, 0.2, 0.04);

                    if(projectile instanceof AbstractArrow) {
                        AbstractArrow arrow = (AbstractArrow) projectile;

                        if(arrow.getPierceLevel() < 1) {
                            arrow.remove();
                        }
                    }
                }, 1);
            }
        }
    }
}
