package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability triggered on
 * an interval.
 */
@AbilityType
public interface Passive {

    /**
     * Useless for most <code>OnAttackedAbility</code>'s.
     *
     * @param player: The player who is activating the ability
     */
    void activate(Player player);
    int getCooldown();
    String getDescription();
    boolean cooldownModifiable();
}
