package survivaltweaks.infinitefunproject.World.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class DestroyProjectiles implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        Block block = event.getHitBlock();
        Projectile proj = event.getEntity();

        if(block == null) {
            return;
        }

        if(proj.getType().equals(EntityType.ARROW) || proj.getType().equals(EntityType.SPECTRAL_ARROW)) {
            if(proj.getShooter() instanceof Player) {
                return;
            }
            proj.remove();
        }
    }
}
