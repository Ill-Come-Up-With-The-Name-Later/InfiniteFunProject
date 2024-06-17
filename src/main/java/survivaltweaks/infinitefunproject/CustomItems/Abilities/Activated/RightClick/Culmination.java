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

import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.stun;

public class Culmination implements ActivatedAbility {
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

        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(16, 16, 16);

        for(int i = 2; i <= 16; i += 2) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                drawCircle(player.getLocation(), finalI, Particle.DRAGON_BREATH, 180);
                drawCircle(player.getLocation(), finalI, Particle.SPELL_WITCH, 180);
                drawCircle(player.getLocation(), finalI, Particle.SMOKE_NORMAL, 180);
                drawCircle(player.getLocation(), finalI, Particle.CRIT_MAGIC, 180);
            }, i * 4L);
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 3, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0, false, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1, false, false, false));

        for(Entity e : entities) {
            if(ignore.contains(e.getType())) {
                continue;
            }
            if(e instanceof Tameable) {
                if (((Tameable) e).isTamed()) {
                    continue;
                }
            }
            if(e instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e;
                entity.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 50, .25, .25, .25, 0.15);
                entity.setVelocity(new Vector(0, 1.8, 0));

                entity.setFireTicks(40);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2, false, false, false));

                int level = OnSpawn.getLevel(entity);

                for(int i = 1; i < 3 + (level / 50); i++) {
                    Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                        if(!entity.isDead()) {
                            entity.damage((calculateDamage(level) / 7), player);
                            drawCircle(entity.getLocation(), 1.2, Particle.DRAGON_BREATH, 90);
                            drawCircle(entity.getLocation(), 1.2, Particle.SPELL_WITCH, 90);
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
        return (Math.ceil(evaluateIntegralLn(14 + level)) + (level * 1.25));
    }

    private double evaluateIntegralLn(double x) {
        return x * Math.log(x) - x;
    }
}
