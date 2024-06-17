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
            Wither wither = (Wither) entity;

            double x = wither.getWorld().getEnderDragonBattle().getEndPortalLocation().getX() + 0.5;
            double y = wither.getWorld().getEnderDragonBattle().getEndPortalLocation().getY() + 4;
            double z = wither.getWorld().getEnderDragonBattle().getEndPortalLocation().getZ() + 0.5;

            for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                Item scroll = wither.getWorld().dropItem(new Location(wither.getWorld(), x, y, z), ItemManager.decrepitScroll);
                scroll.setInvulnerable(true);
                scroll.setGlowing(true);
            }
        }
    }
}
