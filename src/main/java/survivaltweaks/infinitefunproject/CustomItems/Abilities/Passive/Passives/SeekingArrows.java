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
import survivaltweaks.infinitefunproject.CustomItems.Metadata.SeekingMetadata;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class SeekingArrows implements Passive, Listener {

    @Override
    public void activate(Player player) {}

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Arrows seek targets.";
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

        if(PassiveAbility.hasAbility(item, PassiveAbility.SEEKING_ARROWS)) {
            AbstractArrow arrow = (AbstractArrow) projectile;

            arrow.setMetadata("Seeking", new SeekingMetadata(9, 200, InfiniteFunProject.allExceptPlayers()));
        }
    }
}
