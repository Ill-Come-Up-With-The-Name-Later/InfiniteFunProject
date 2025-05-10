package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackedAbility;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class BuildArmor implements MonsterAbility {

    @Override
    public void activate(Monster monster) {
        monster.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(Math.min(15,
                monster.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + 1));
        monster.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(Math.min(15,
                monster.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue() + 1));
    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {

    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "BuildArmor";
    }
}
