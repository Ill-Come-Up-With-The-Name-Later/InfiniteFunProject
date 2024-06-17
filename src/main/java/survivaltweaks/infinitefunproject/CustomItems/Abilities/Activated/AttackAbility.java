package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

/**
 * An ability triggered by
 * attacking.
 */
@AbilityType
public interface AttackAbility extends ActivatedAbility {

    /**
     * Useless for some <code>ActivatedAbility</code>'s.
     *
     * @param player: The player who is activating the ability
     * @param entity: The entity that <code>player</code> is attacking
     */
    void activate(Player player, LivingEntity entity);
    void activate(Player player, EntityDamageByEntityEvent event);
    int getCooldown();
    String getDescription();
    boolean oneTimeUse();
}
