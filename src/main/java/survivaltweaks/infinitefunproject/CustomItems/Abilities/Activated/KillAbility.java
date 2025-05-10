package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability triggered by
 * killing an entity
 */
@AbilityType
public interface KillAbility extends ActivatedAbility {

    /**
     * Useless for some <code>ActivatedAbility</code>'s.
     *
     * @param player: The player who is activating the ability
     * @param entity: The entity that <code>player</code> is attacking
     */
    void activate(Player player, LivingEntity entity);
    int getCooldown();
    String getDescription();
    boolean oneTimeUse();
}
