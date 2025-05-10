package survivaltweaks.infinitefunproject.MonsterAbilities;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.AbilityType;

@AbilityType
public interface MonsterAbility {

    void activate(Monster monster);
    void activate(Monster monster, LivingEntity entity);
    void activate(EntityDamageByEntityEvent event);
    String abilityName();
}
