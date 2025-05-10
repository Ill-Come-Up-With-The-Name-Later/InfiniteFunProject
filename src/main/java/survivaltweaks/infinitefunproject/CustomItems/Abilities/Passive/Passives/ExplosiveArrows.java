package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility;
import survivaltweaks.infinitefunproject.CustomItems.Metadata.ExplosiveMeta;

public class ExplosiveArrows implements Passive, Listener {

    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Arrows explode on contact\nwith anything.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if(!(projectile.getShooter() instanceof Player)) {
            return;
        }

        Player player = (Player) projectile.getShooter();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(PassiveAbility.hasAbility(item, PassiveAbility.EXPLOSIVE_ARROW)) {
            AbstractArrow arrow = (AbstractArrow) projectile;

            arrow.setMetadata("Explosive", new ExplosiveMeta(3.7f, false, false));
        }
    }
}
