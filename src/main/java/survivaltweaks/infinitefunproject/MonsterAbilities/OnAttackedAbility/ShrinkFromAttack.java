package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class ShrinkFromAttack implements MonsterAbility {

    @Override
    public void activate(Monster monster) {
        monster.getAttribute(Attribute.SCALE).setBaseValue(monster.getAttribute(Attribute.SCALE).getBaseValue() - 0.025);

        if(monster.getAttribute(Attribute.SCALE).getBaseValue() < 0.1) {
            monster.remove();
        }
    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {

    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "Shrink Attack";
    }
}
