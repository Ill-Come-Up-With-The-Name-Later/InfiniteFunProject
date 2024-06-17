package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.makeInvisible;

public class Cloak implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        makeInvisible(player, 160);
    }

    @Override
    public int getCooldown() {
        return 320;
    }

    @Override
    public String getDescription() {
        return "Become invisible for 8 seconds.";
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
