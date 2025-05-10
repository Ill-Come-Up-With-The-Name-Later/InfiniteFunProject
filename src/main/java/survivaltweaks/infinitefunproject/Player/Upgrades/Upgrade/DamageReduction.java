package survivaltweaks.infinitefunproject.Player.Upgrades.Upgrade;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import survivaltweaks.infinitefunproject.Player.Upgrades.PlayerUpgrade;

import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.getUpgradeLevel;

public class DamageReduction implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof Player) {
            Player player = (Player) damaged;

            event.setDamage(event.getDamage() * (1 - ((double) getUpgradeLevel(player, PlayerUpgrade.DAMAGE_REDUCTION) / 40)));
        }
    }
}
