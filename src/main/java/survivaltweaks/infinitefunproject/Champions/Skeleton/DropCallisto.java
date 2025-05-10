package survivaltweaks.infinitefunproject.Champions.Skeleton;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropCallisto implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Skeleton && entity.hasMetadata("Champion")) {
            event.getDrops().add(ItemManager.callisto);
        }
    }
}
