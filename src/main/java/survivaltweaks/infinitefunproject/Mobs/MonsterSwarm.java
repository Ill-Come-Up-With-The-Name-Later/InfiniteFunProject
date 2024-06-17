package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.Mobs.OnSpawn.getLevel;

public class MonsterSwarm implements Listener {

    @EventHandler
    public void onMobHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(!(damaged instanceof Monster)) {
            return;
        }

        if(!(damager instanceof LivingEntity || damager instanceof Projectile)) {
            return;
        }

        if(!(damager instanceof Projectile)) {
            LivingEntity entity = (LivingEntity) damager;
            Monster hit = (Monster) damaged;

            ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(hit, searchRadius(hit));

            if(hit.getType() == entity.getType()) {
                return;
            }

            for(Entity entity1 : nearby) {
                if(entity1.getType() == hit.getType()) {
                    Monster monster = (Monster) entity1;

                    if(monster.getTarget() == null) {
                        monster.setTarget(entity);
                    }
                }
            }
        } else {
            if(!(((Projectile) damager).getShooter() instanceof LivingEntity)) {
                return;
            }

            LivingEntity entity = (LivingEntity) ((Projectile) damager).getShooter();
            Monster hit = (Monster) damaged;

            ArrayList<Entity> nearby = (ArrayList<Entity>) circularNearbyEntities(hit, searchRadius(hit));

            if(hit.getType() == entity.getType()) {
                return;
            }

            for(Entity entity1 : nearby) {
                if (entity1.getType() == hit.getType()) {
                    Monster monster = (Monster) entity1;

                    if (monster.getTarget() == null) {
                        monster.setTarget(entity);
                    }
                }
            }
        }
    }

    public double searchRadius(Monster monster) {
        return 25 * Math.pow(1.1, (double) (getLevel(monster) - 50) / 10);
    }
}
