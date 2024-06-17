package survivaltweaks.infinitefunproject.Champions.Creeper;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EnlargeBlastRadius implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Creeper) {
            Creeper creeper = (Creeper) entity;

            if(creeper.hasMetadata("Champion")) {
                creeper.setExplosionRadius(creeper.getExplosionRadius() * 5);
                creeper.setMaxFuseTicks(creeper.getMaxFuseTicks() / 2);
            }
        }
    }
}
