package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class LaunchNuke implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setInvisible(true);
        arrow.setVisibleByDefault(false);
        addProjectileTrail(arrow, Unusual.ROCKET_TRAIL);

        new BukkitRunnable() {

            @Override
            public void run() {
                if(arrow.isDead()) {
                    cancel();
                    return;
                }

                if(arrow.isOnGround() || arrow.getTicksLived() >= 60) {
                    createNuke(arrow.getLocation(), 66, 750, player);
                    arrow.remove();
                    cancel();
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    @Override
    public int getCooldown() {
        return 200;
    }

    @Override
    public String getDescription() {
        return "Launches a large nuke that\ndetonates after 3 seconds or\nwhen it lands.\n\n" + color("&cCan damage the user.");
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
