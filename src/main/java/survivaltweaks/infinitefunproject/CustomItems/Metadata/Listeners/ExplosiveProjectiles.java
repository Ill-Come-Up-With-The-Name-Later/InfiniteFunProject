package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.MetadataValue;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;

import java.util.List;

public class ExplosiveProjectiles implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof LivingEntity) {
            LivingEntity shooter = (LivingEntity) projectile.getShooter();

            if(projectile.hasMetadata("Explosive")) {
                List<MetadataValue> explosiveMeta = projectile.getMetadata("Explosive");
                ExplosiveMeta meta = (ExplosiveMeta) explosiveMeta.get(0);

                projectile.getWorld().createExplosion(projectile.getLocation(), meta.getExplosionPower(), meta.isCauseFire(),
                        meta.isDestroyBlocks(), shooter);

                if(projectile instanceof AbstractArrow) {
                    AbstractArrow arrow = (AbstractArrow) projectile;

                    if(arrow.getPierceLevel() < 1) {
                        arrow.remove();
                    }
                }
            }
        }
    }
}
