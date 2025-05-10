package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.Combat.RandomCrits.DealRandomCrit.giveMiniCrits;

public class MiniCritCombo implements MonsterAbility {

    @Override
    public void activate(Monster monster) {
        giveMiniCrits(monster, 40);
    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {

    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "";
    }
}
