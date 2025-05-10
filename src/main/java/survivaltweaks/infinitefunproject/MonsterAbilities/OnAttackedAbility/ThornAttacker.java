package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class ThornAttacker implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {

    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {
        double damage = event.getDamage();
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();

        if(damager instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) damager;

            entity.damage(damage / 2, damaged);
        }
    }

    @Override
    public String abilityName() {
        return "ThornAbility";
    }
}
