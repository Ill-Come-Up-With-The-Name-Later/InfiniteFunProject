package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Attacks;

import org.bukkit.Particle;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class ProjectileShield implements Listener {

    /**
     * Dragon spawn and shield
     * loop
     *
     * @param event: Entity spawn event
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) spawned;

            ArrayList<EntityType> projectiles = new ArrayList<>();
            projectiles.add(EntityType.ARROW);
            projectiles.add(EntityType.TRIDENT);

            new BukkitRunnable() {

                @Override
                public void run() {
                    if(dragon.isDead()) {
                        cancel();
                        return;
                    }
                    ArrayList<Entity> entities = (ArrayList<Entity>) dragon.getNearbyEntities(7, 7, 7);

                    for(Entity entity : entities) {
                        if(projectiles.contains(entity.getType())) {
                            entity.getWorld().spawnParticle(Particle.DRAGON_BREATH, entity.getLocation(), 25, 0.4, 0.4, 0.4, 0.04);
                            entity.remove();
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
        }
    }
}
