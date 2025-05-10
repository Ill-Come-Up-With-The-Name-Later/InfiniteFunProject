package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.setFire;

public class IgniteAttacker implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        setFire(entity, 240);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "IgniteAttacker";
    }
}
