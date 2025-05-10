package survivaltweaks.infinitefunproject.Bosses.EnderDragon.Crystals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class ProjectileImmunity implements Listener {

    /**
     * Make end crystals immune to
     * projectiles
     *
     * @param event: Projectile shot
     */
    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Player) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    if(projectile.isDead() || projectile.isOnGround()) {
                        cancel();
                        return;
                    }
                    
                    ArrayList<Entity> nearby = (ArrayList<Entity>) projectile.getNearbyEntities(10, 10, 10);

                    for(Entity e : nearby) {
                        if(e instanceof EnderCrystal) {
                            EnderCrystal crystal = (EnderCrystal) e;
                            if(crystal.hasMetadata("PlayerPlace")) {
                                return;
                            }
                            Location loc = projectile.getLocation();
                            loc.subtract(new Vector(0, 2, 0));
                            crystal.setBeamTarget(loc);
                            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> crystal.setBeamTarget(null), 10);
                            projectile.remove();
                        }
                    }
                }
            }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
        }
    }
}
