package survivaltweaks.infinitefunproject.Mobs.Skeleton;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AutoAimArrows implements Listener {

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if(projectile instanceof Arrow && shooter instanceof AbstractSkeleton) {
            long day = shooter.getWorld().getFullTime() / 24000;
            int level = (int) Math.min(Math.floor((double) day / 10), 50);

            if(level >= 12) {
                ((Arrow) projectile).setCritical(true);
            }
            if(level >= 18) {
                projectile.setFireTicks(-1);
            }

            projectile.setMetadata("Seeking", new SeekingMetadata(4, 200, InfiniteFunProject.players()));
        }
    }
}
