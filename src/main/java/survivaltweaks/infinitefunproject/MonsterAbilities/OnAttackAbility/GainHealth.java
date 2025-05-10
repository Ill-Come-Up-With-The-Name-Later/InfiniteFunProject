package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class GainHealth implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        drawCircle(monster.getLocation(), 1.5, Particle.SOUL_FIRE_FLAME, 90);

        monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(
                monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 1.025);
        monster.setHealth(monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "GainHealth";
    }
}
