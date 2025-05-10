package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.OnSpawn;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.stun;

public class SuperHammer implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(9, 9, 9);

        for(Entity e : entities) {
            if(!(e instanceof LivingEntity)) {
                continue;
            }

            if(e instanceof Tameable) {
                if(((Tameable) e).isTamed()) {
                    continue;
                }
            }

            LivingEntity entity = (LivingEntity) e;
            entity.getWorld().spawnParticle(Particle.LARGE_SMOKE, entity.getLocation(), 50, .25, .25, .25, 0.15);
            entity.setVelocity(new Vector(0, 1.8, 0));

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                entity.setVelocity(new Vector(0, -1.5, 0));

                int level = OnSpawn.getLevel(entity);
                entity.damage(calculateDamage(level), player);

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                        () -> stun(entity, 180, false), 10);
            }, 14);
        }
    }

    @Override
    public int getCooldown() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Throws enemies into the air,\ndealing a percentage of their\nhealth as damage.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    private double calculateDamage(int level) {
        return Math.ceil(evaluateIntegralLn(14 + level) - evaluateIntegralLn(2.75));
    }

    private double evaluateIntegralLn(double x) {
        return x * Math.log(x) - x;
    }
}
