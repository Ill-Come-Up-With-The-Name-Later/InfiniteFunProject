package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class GrowOffAttack implements MonsterAbility {

    @Override
    public void activate(Monster monster) {
        monster.getAttribute(Attribute.SCALE).setBaseValue(monster.getAttribute(Attribute.SCALE).getBaseValue() + 0.07);
    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {

    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "GrowAttack";
    }
}
