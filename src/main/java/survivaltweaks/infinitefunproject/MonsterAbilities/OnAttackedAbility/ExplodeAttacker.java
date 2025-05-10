package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class ExplodeAttacker implements MonsterAbility {

    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        entity.getWorld().createExplosion(entity.getLocation(), 3.1f, false, false, monster);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "ExplodeAttacker";
    }
}
