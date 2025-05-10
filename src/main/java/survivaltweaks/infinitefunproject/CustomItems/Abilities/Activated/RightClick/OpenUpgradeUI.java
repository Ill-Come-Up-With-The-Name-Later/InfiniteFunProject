package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.Player.Upgrades.UpgradeGUI;

public class OpenUpgradeUI implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        UpgradeGUI.openGUI(player);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Allows you to buy upgrades for\nyour stats.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
