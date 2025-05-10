package survivaltweaks.infinitefunproject.Mobs.Wither;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropScroll implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Wither) {
            for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                event.getDrops().add(ItemManager.decrepitScroll);
                event.getDrops().add(ItemManager.soulBoots);
            }
        }
    }
}
