package survivaltweaks.infinitefunproject.Mobs.Warden;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropWardenArmor implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Warden) {
            event.getDrops().add(ItemManager.wardenChestplate);
        }
    }
}
