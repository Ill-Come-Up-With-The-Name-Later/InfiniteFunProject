package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.applyDOT;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawExpandingCircle;

public class ScorchTarget implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        drawExpandingCircle(entity, 1, 5, 0.2, 25, Particle.FLAME);
        applyDOT(monster, entity, 2, 5, 25);
        entity.setVisualFire(true);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.setVisualFire(false), 125);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "ScorchTarget";
    }
}
