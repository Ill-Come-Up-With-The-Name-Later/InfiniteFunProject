package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class ElectricStrike implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        drawCircle(entity.getLocation(), 1.5, Particle.ELECTRIC_SPARK, 90);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.getWorld().strikeLightning(entity.getLocation()), 8);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "";
    }
}
