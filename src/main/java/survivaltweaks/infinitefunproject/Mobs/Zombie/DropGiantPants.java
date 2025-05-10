package survivaltweaks.infinitefunproject.Mobs.Zombie;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropGiantPants implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;

            if(zombie.getAttribute(Attribute.GENERIC_SCALE).getBaseValue() > 1.5) {
                event.getDrops().add(ItemManager.giantPants);
            }
        }
    }
}
