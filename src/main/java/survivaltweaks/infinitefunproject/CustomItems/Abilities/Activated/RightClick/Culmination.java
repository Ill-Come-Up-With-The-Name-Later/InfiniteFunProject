package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.OnSpawn;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.*;

public class Culmination implements ActivatedAbility {

    @Override
    public void activate(Player player) {
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(16, 16, 16);

        ArrayList<Particle> particles = new ArrayList<>() {
            {
                add(Particle.DRAGON_BREATH);
                add(Particle.WITCH);
                add(Particle.SMOKE);
                add(Particle.ENCHANTED_HIT);
            }
        };

        ArrayList<Particle> entityParticles = new ArrayList<>() {
            {
                add(Particle.DRAGON_BREATH);
                add(Particle.WITCH);
            }
        };

        drawExpandingCircle(player, 2, 7, 2, 4, particles);

        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 400, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 3, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 600, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1, false, false, false));

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

            entity.setFireTicks(40);
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 600, 2, false, false, false));

            int level = OnSpawn.getLevel(entity);

            for(int i = 1; i < 3 + (level / 50); i++) {
                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(!entity.isDead()) {
                        entity.damage((calculateDamage(level) / 7), player);
                        drawCircle(entity.getLocation(), 1.2, entityParticles, 90);
                    }
                }, i * 20);
            }

            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                if(!entity.isDead()) {
                    entity.setVelocity(new Vector(0, -1.5, 0));

                    entity.getWorld().createExplosion(entity.getLocation(), 2.5f, false, false, player);

                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin,
                            () -> stun(entity, 200, false), 20);
                }
            }, 10);
        }
    }

    @Override
    public int getCooldown() { return 480; }

    @Override
    public String getDescription() { return "The combined might of the\nEnder Dragon and Wither\nall in one sword..."; }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return true;
    }

    private double calculateDamage(int level) {
        return (Math.ceil(evaluateIntegralLn(14 + level)) + (level * 1.25)) * 1.1;
    }

    private double evaluateIntegralLn(double x) {
        return x * Math.log(x) - x;
    }
}
