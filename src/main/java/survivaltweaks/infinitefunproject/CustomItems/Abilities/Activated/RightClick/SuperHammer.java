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
        ArrayList<EntityType> ignore = new ArrayList<>();
        ignore.add(EntityType.ITEM_FRAME);
        ignore.add(EntityType.DROPPED_ITEM);
        ignore.add(EntityType.GLOW_ITEM_FRAME);
        ignore.add(EntityType.FIREBALL);
        ignore.add(EntityType.SMALL_FIREBALL);
        ignore.add(EntityType.DRAGON_FIREBALL);
        ignore.add(EntityType.FIREWORK);
        ignore.add(EntityType.FISHING_HOOK);
        ignore.add(EntityType.ARMOR_STAND);
        ignore.add(EntityType.TEXT_DISPLAY);
        ignore.add(EntityType.ARROW);
        ignore.add(EntityType.SPECTRAL_ARROW);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.EGG);
        ignore.add(EntityType.SNOWBALL);
        ignore.add(EntityType.ENDER_CRYSTAL);
        ignore.add(EntityType.SHULKER_BULLET);
        ignore.add(EntityType.PLAYER);
        ignore.add(EntityType.WITHER);
        ignore.add(EntityType.ENDER_DRAGON);
        ignore.add(EntityType.AREA_EFFECT_CLOUD);
        ignore.add(EntityType.LEASH_HITCH);

        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(9, 9, 9);

        for(Entity e : entities) {
            if(ignore.contains(e.getType())) {
                continue;
            }
            if(e instanceof Tameable) {
                if(((Tameable) e).isTamed()) {
                    continue;
                }
            }
            if(e instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e;
                entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 50, .25, .25, .25, 0.15);
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
