package survivaltweaks.infinitefunproject.Mobs.ElderGuardian;

import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropKingTrident implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof ElderGuardian) {
            event.getDrops().add(ItemManager.kingTrident);
        }
    }
}
