package survivaltweaks.infinitefunproject.Champions.Zombie;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.RevivedMeta;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class ResurrectKilled implements Listener {

    @EventHandler
    public void onEntityDies(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if(entity instanceof Monster) {
            ArrayList<Entity> entities = (ArrayList<Entity>) entity.getNearbyEntities(10, 10, 10);

            for(Entity e : entities) {
                if(e instanceof Zombie) {
                    Zombie zombie = (Zombie) e;

                    if(zombie.hasMetadata("Champion")) {
                        if(new Random().nextBoolean()) {
                            necromancy(entity);
                            zombie.getWorld().spawnParticle(Particle.SPELL_WITCH, zombie.getLocation().add(0, 1, 0),
                                    20, 0.3, 0.3, 0.3, 0.03);
                        }
                    }
                }
            }
        }
    }

    public void necromancy(LivingEntity entity) {
        if(!(entity instanceof Slime) && !entity.hasMetadata("Revived")) {
            LivingEntity revived = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), entity.getType());

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                revived.setMetadata("Revived", new RevivedMeta());
                drawCircle(revived.getLocation(), 2, Particle.TOTEM, 180);
                revived.setHealth(revived.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.75);
            }, 10);
        }
    }
}
