package survivaltweaks.infinitefunproject.Mobs.Blaze;

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

public class AutoAimFireballs implements Listener {

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        Entity shooter = (Entity) projectile.getShooter();

        if(shooter instanceof Blaze && projectile instanceof SmallFireball) {
            projectile.setMetadata("Seeking", new SeekingMetadata(4, 360, InfiniteFunProject.players()));
        }
    }
}
