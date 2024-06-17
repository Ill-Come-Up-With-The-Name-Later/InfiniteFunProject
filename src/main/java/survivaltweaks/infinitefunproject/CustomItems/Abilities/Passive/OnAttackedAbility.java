package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability triggered when attacked.
 */
@AbilityType
public interface OnAttackedAbility extends Passive {

    /**
     * Useless for some <code>Passive</code>s.
     * May be redundant for most.
     *
     * @param player: The player who is activating the ability
     * @param attacker: The attacking entity
     */
    void activate(Player player, LivingEntity attacker);
    int getCooldown();
    String getDescription();
    boolean oneTimeUse();
}
