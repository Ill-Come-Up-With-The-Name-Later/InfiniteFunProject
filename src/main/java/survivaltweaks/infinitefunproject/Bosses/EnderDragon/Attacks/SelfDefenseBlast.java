package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks;

import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class SelfDefenseBlast implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) damaged;
            if(dragon.getPhase() == EnderDragon.Phase.HOVER || dragon.getPhase() == EnderDragon.Phase.SEARCH_FOR_BREATH_ATTACK_TARGET
            || dragon.getPhase() == EnderDragon.Phase.BREATH_ATTACK) {
                if(new Random().nextInt(0, 100) <= 45) {
                    ArrayList<Entity> entities = (ArrayList<Entity>) dragon.getNearbyEntities(12, 12, 12);

                    if(!entities.isEmpty()) {
                        dragon.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, dragon.getLocation(), 10, .1, .1, .1, 12);
                    }

                    for(Entity e : entities) {
                        if(e instanceof LivingEntity) {
                            LivingEntity entity = (LivingEntity) e;

                            entity.damage(6, dragon);
                            Vector direction = entity.getLocation().getDirection();
                            entity.setVelocity(direction.multiply(-1.5).normalize().add(new Vector(0, 1.7, 0)));
                        }
                        if(e instanceof Projectile) {
                            e.remove();
                        }
                    }
                }
            }
        }
    }
}
