package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability triggered by
 * a left or right click.
 */
@AbilityType
public interface ActivatedAbility {

    /**
     * Useless for any <code>AttackAbility</code>.
     *
     * @param player: The player who is activating the ability
     */
    void activate(Player player);
    int getCooldown();
    String getDescription();
    boolean oneTimeUse();
    boolean cooldownModifiable();
}
