package survivaltweaks.infinitefunproject.MonsterAbilities.OnAttackAbility;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.MonsterAbilities.MonsterAbility;

public class WindCharge implements MonsterAbility {
    @Override
    public void activate(Monster monster) {

    }

    @Override
    public void activate(Monster monster, LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WIND_CHARGED, 400, 0, false, true, true));
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                () -> entity.setVelocity(new Vector(0, 1.5, 0)), 2);
    }

    @Override
    public void activate(EntityDamageByEntityEvent event) {

    }

    @Override
    public String abilityName() {
        return "WindChargeTarget";
    }
}
