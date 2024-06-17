package survivaltweaks.infinitefunproject.Champions.Spider;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WebPlayers implements Listener {

    @EventHandler
    public void onDamaged(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damaged instanceof Spider) {
            Spider spider = (Spider) damaged;

            if(spider.hasMetadata("Champion")) {
                damager.getWorld().setBlockData(damager.getLocation(), Material.COBWEB.createBlockData());
            }
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

        if(damager instanceof Spider) {
            Spider spider = (Spider) damager;

            if(spider.hasMetadata("Champion")) {
                damaged.getWorld().setBlockData(damaged.getLocation(), Material.COBWEB.createBlockData());
            }
        }
    }
}
