package survivaltweaks.infinitefunproject.Mobs.Creeper;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class AutoCharge implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Creeper) {
            Creeper creeper = (Creeper) entity;

            creeper.setPowered(true);
        }
    }
}
