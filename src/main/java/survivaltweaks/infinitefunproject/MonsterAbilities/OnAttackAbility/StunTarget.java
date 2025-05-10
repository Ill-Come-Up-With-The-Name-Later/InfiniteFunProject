package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.stun;

public class StunTarget implements MonsterAbility {

    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        stun(entity, 20, false);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "StunTarget";
    }
}
