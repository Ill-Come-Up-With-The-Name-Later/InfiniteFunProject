package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;

public class Wait implements Passive {
    @Override
    public void activate(Player player) {

    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Waits for this item to release,\nreplacing this placeholder item.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
