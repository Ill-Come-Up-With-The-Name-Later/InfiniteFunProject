package survivaltweaks.infinitefunproject.Player.Upgrades.Upgrade;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.Player.Upgrades.PlayerUpgrade;

import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.getUpgradeLevel;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if(damager instanceof Player) {
            Player player = (Player) damager;

            event.setDamage(event.getDamage() + ((double) getUpgradeLevel(player, PlayerUpgrade.DAMAGE) * 0.7));
        }
    }
}
