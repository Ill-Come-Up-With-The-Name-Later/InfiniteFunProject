package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.World.Infection.InfectionManager.grantImmunity;

public class Vaccine implements ActivatedAbility {

    int duration = 7200;

    @Override
    public void activate(Player player) {
        grantImmunity(player, duration);
    }

    @Override
    public int getCooldown() {
        return (int) (duration * 1.33);
    }

    @Override
    public String getDescription() {
        return "Grants temporary immunity to\nthe Coronavirus.\n\nThe immunity lasts " + duration / 20 + " seconds.";
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
