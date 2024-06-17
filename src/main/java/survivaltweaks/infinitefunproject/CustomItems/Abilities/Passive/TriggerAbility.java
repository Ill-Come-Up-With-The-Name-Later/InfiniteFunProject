package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability only code can
 * trigger
 */
@AbilityType
public interface TriggerAbility extends Passive {

    void activate(Player player);
    int getCooldown();
    String getDescription();
    boolean cooldownModifiable();
}
