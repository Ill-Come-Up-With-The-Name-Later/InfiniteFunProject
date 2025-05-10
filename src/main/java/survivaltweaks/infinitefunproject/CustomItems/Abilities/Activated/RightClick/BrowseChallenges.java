package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.ChallengeMode.UI.ChallengeModeUI;

public class BrowseChallenges implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ChallengeModeUI.open(player);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Allows you to enable\nchallenges.\n\nYou cannot undo this.";
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
