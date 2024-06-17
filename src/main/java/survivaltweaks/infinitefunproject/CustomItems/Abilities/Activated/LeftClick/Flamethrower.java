package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.LeftClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.createDamageRay;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.createSpreadDamageRay;

public class Flamethrower implements ActivatedAbility {
    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.FLAME);
                add(Particle.FALLING_LAVA);
            }
        };

        for(int i = 0; i < 3; i++) {
            HashMap<LivingEntity, Boolean> entities = createSpreadDamageRay(player, 0.25, 15, 6, 99999,
                    true, false, false, particles, false);

            for (LivingEntity entity : entities.keySet()) {
                if (!entity.isDead()) {
                    entity.setFireTicks(400);
                    entity.setVisualFire(true);
                    entity.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);

                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        if (!entity.isDead()) {
                            entity.setVisualFire(false);
                        }
                    }, 400);
                }
            }
        }
    }

    @Override
    public int getCooldown() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Shoots out three powerful jets\nof fire.";
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
