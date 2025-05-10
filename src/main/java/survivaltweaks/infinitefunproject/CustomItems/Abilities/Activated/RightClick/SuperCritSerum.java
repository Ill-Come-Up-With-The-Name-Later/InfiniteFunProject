package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveGuaranteedSuperCrits;

public class SuperCritSerum implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        giveGuaranteedSuperCrits(player, 240);
    }

    @Override
    public int getCooldown() {
        return 1200;
    }

    @Override
    public String getDescription() {
        return "Gives the user Super Critical\nHits for 12 seconds.";
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
