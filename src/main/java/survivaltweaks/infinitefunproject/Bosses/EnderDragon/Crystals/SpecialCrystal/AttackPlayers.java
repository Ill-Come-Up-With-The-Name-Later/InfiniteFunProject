package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals.SpecialCrystal;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class AttackPlayers implements Listener {

    /**
     * Special central crystal creation
     *
     * @param event: Entity spawn event
     */
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity spawn = event.getEntity();

        if(spawn instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) spawn;

            new BukkitRunnable() {

                @Override
                public void run() {
                    if(crystal.isDead()) {
                        cancel();
                    }

                    if(crystal.hasMetadata("Attacker")) {
                        drawCircle(crystal.getLocation().subtract(new Vector(0, 4, 0)), 8.1, Particle.ENCHANTED_HIT, 90);

                        drawCircle(crystal.getLocation().subtract(new Vector(0, 4, 0)), 26.3, Particle.WITCH, 180);
                        drawCircle(crystal.getLocation().subtract(new Vector(0, 4, 0)), 26.3, Particle.DRAGON_BREATH, 180);
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 2, 2);

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(crystal.hasMetadata("Attacker")) {
                    crystal.setGlowing(true);
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            if(crystal.isDead()) {
                                cancel();
                                return;
                            }

                            ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(crystal, 26);

                            for(Entity entity : entities) {
                                if(entity instanceof Player) {
                                    Player player = ((Player) entity).getPlayer();
                                    Location loc = player.getLocation();
                                    loc.subtract(new Vector(0, 1.8, 0));
                                    if(distanceBetween(player.getLocation().toVector(), crystal.getLocation().toVector()) > 8) {
                                        crystal.setBeamTarget(loc);
                                        player.damage(4, crystal);
                                        drawCircle(player.getLocation().add(new Vector(0, 1, 0)), 1.4, Particle.ENCHANTED_HIT, 90);
                                    }
                                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> crystal.setBeamTarget(null), 10);
                                }
                            }

                            ArrayList<Entity> nearby = (ArrayList<Entity>) crystal.getNearbyEntities(2, 2, 2);

                            for(Entity e : nearby) {
                                if(e instanceof EnderDragon || e instanceof EnderCrystal) {
                                    crystal.remove();
                                }
                            }

                        }
                    }.runTaskTimer(InfiniteFunProject.plugin, 65, 65);
                }
            }, 4);
        }
    }
}
