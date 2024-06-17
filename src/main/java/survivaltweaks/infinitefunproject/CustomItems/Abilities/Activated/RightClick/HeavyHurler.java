package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.HashMap;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class HeavyHurler implements ActivatedAbility {
    int range = 100;

    @Override
    public void activate(Player player) {
        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.CRIT_MAGIC);
                add(Particle.ELECTRIC_SPARK);
            }
        };

        drawCircle(player.getLocation(), 1.5, Particle.SOUL_FIRE_FLAME, 90);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            HashMap<LivingEntity, Boolean> hitEntities =
                    createDamageRay(player, range, 40, 0, true, false, false, particles,
                            false);

            for(LivingEntity entity : hitEntities.keySet()) {
                stun(entity, 40, false);
            }
        }, 15);
    }

    @Override
    public int getCooldown() {
        return 360;
    }

    @Override
    public String getDescription() {
        return "Fires a powerful, charged shot that\nstuns enemies on hit.\n\nCan pierce shields.";
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
