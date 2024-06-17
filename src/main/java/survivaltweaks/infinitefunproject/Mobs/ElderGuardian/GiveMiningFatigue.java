package survivaltweaks.infinitefunproject.Mobs.ElderGuardian;

import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;

public class GiveMiningFatigue implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawned = event.getEntity();

        if(spawned instanceof ElderGuardian) {
            new BukkitRunnable() {
                @Override
                public void run() {

                    if(spawned.isDead()) {
                        cancel();
                    }

                    ArrayList<Entity> entities = (ArrayList<Entity>) spawned.getNearbyEntities(60, 60, 60);

                    for(Entity e : entities) {
                        if(e instanceof Player) {
                            Player player = (Player) e;
                            player.addPotionEffect(PotionEffectType.SLOW_DIGGING.createEffect(400, 3));
                            player.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(400, 1));
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);

            new BukkitRunnable() {

                @Override
                public void run() {
                    ElderGuardian guardian = (ElderGuardian) spawned;
                    ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(spawned, 15);

                    for(Entity entity : entities) {
                        if(entity instanceof Player) {
                            Player player = (Player) entity;

                            if(guardian.hasLineOfSight(player) && guardian.getTarget() == null) {
                                guardian.setTarget(player);
                                guardian.setLaserTicks(15);
                            }
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 25, 25);
        }
    }
}
