package survivaltweaks.infinitefunproject.CustomItems.Metadata.Listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class RemoveGroundedProjectiles implements Listener {

    @EventHandler
    public void onHitGround(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();

        if(projectile.hasMetadata("RemoveOnGround")) {
            if(event.getHitBlock() != null) {
                projectile.remove();
            }

            if(event.getHitEntity() != null) {
                if(projectile instanceof Arrow) {
                    Arrow arrow = (Arrow) projectile;
                    
                    if(arrow.getPierceLevel() < 2) {
                        projectile.remove();

                        if(!arrow.getPassengers().isEmpty()) {
                            for(Entity passenger : arrow.getPassengers()) {
                                passenger.remove();
                            }
                        }
                    }
                }
            }
        }
    }
}
