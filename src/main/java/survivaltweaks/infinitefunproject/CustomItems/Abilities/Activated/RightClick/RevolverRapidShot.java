package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.OnHitAbility;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.hasCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.InitAbilities.setCooldown;
import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.TriggeredAbility.triggerAbility;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;

public class RevolverRapidShot implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.CRIT_MAGIC);
                add(Particle.FIREWORKS_SPARK);
                add(Particle.VILLAGER_HAPPY);
            }
        };

        for(int i = 1; i <= 6; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                HashMap<LivingEntity, Boolean> entities = createDamageRay(player, 60, 0, 0,
                        false, false, false, particles, true);

                for(LivingEntity entity : entities.keySet()) {
                    if(entity instanceof Player) {
                        Player other = (Player) entity;

                        other.setHealth(Math.min(other.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), other.getHealth() + 8));
                    } else {
                        entity.damage(6, player);

                        if(entities.get(entity) && !hasCooldown(player, TriggeredAbility.CRYSTALLIZE)) {
                            triggerAbility(player, TriggeredAbility.CRYSTALLIZE);
                        }

                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, false, false, false));
                    }
                }
            }, i * 3);
        }
    }

    @Override
    public int getCooldown() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "Fires off six shots that heal or\ndeal 6 damage.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }
}
