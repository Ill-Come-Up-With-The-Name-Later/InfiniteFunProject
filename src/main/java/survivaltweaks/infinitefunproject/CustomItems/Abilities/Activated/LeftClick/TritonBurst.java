package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

public class TritonBurst implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        for(int i = 1; i <= 15; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                player.launchProjectile(Arrow.class);
            }, i * 2);
        }
    }

    @Override
    public int getCooldown() {
        return 240;
    }

    @Override
    public String getDescription() {
        return "Rapidly fire off 15 arrows.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
