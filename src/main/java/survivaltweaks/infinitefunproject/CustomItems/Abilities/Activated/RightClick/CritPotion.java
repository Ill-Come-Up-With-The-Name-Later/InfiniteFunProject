package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveCrits;

public class CritPotion implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        giveCrits(player, 12 * 20);
    }

    @Override
    public int getCooldown() {
        return 1200;
    }

    @Override
    public String getDescription() {
        return "Grants you Critical Hits for 12\nseconds.";
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
