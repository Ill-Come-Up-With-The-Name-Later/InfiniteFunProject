package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.taxEvade;

public class DamageTaxExemption implements ActivatedAbility {
    int duration = 60;

    @Override
    public void activate(Player player) {
        taxEvade(player, duration * 20, true);
    }

    @Override
    public int getCooldown() {
        return 4800;
    }

    @Override
    public String getDescription() {
        return "Dodges the the damage tax\nfor " + duration + " seconds.";
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
