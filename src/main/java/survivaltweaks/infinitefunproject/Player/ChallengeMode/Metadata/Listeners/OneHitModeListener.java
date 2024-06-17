package survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.Metadata.OneHitMode;

public class OneHitModeListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager.hasMetadata("OneHitMode")) {
            event.setDamage(Integer.MAX_VALUE);
        }
    }

    @EventHandler
    public void projectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.getShooter() instanceof Entity) {
            Entity entity = (Entity) projectile.getShooter();

            if(entity.hasMetadata("OneHitMode")) {
                projectile.setMetadata("OneHitMode", new OneHitMode());
            }
        }
    }
}
