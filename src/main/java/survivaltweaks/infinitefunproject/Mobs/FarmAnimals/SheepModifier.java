package survivaltweaks.infinitefunproject.Mobs.FarmAnimals;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SheepModifier implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Sheep) {
            Sheep sheep = (Sheep) entity;

            sheep.setSheared(true);
            sheep.setCustomName("jeb_");
        }
    }
}
