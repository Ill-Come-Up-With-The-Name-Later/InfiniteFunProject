package survivaltweaks.infinitefunproject.Mobs.Rabbit;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class TurnLethal implements Listener {

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Rabbit) {
            Rabbit rabbit = (Rabbit) entity;
            rabbit.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
            rabbit.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            rabbit.setHealth(20);
        }
    }
}
