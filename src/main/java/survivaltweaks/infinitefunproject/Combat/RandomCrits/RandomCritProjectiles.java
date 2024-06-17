package survivaltweaks.infinitefunproject.Combat.RandomCrits;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.stun;

public class RandomCritProjectiles implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damaged instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) damaged;

            if(damager instanceof Projectile) {
                Projectile projectile = (Projectile) damager;

                if(projectile.hasMetadata("EntityCrit")) {
                    stun(entity, 20, false);
                }
            }
        }
    }
}
