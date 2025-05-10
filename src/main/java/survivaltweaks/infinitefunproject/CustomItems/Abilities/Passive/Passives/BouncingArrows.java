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
import survivaltweaks.infinitefunproject.CustomItems.Metadata.BounceProjectiles;

public class BouncingArrows implements Passive, Listener {

    @Override
    public void activate(Player player) {}

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Arrows become bouncy.\n\nArrows expire after 12\nseconds.";
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

        if(PassiveAbility.hasAbility(item, PassiveAbility.BOUNCING_ARROWS)) {
            AbstractArrow arrow = (AbstractArrow) projectile;

            arrow.setMetadata("Bouncy", new BounceProjectiles(7, 300));
        }
    }
}
