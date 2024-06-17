package survivaltweaks.infinitefunproject.Mobs.EnderDragon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class DropSword implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) entity;

            double x = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getX() + 0.5;
            double y = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getY() + 4;
            double z = dragon.getWorld().getEnderDragonBattle().getEndPortalLocation().getZ() + 0.5;

            for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                Item sword = dragon.getWorld().dropItem(new Location(dragon.getWorld(), x, y, z), ItemManager.dragonSword);
                sword.setGlowing(true);
            }
        }
    }
}
