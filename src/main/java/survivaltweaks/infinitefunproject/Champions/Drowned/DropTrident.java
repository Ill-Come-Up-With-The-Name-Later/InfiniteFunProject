package survivaltweaks.infinitefunproject.Champions.Drowned;

import org.bukkit.entity.Drowned;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropTrident implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Drowned && entity.hasMetadata("Champion")) {
            event.getDrops().add(ItemManager.kingTrident);
        }
    }
}
