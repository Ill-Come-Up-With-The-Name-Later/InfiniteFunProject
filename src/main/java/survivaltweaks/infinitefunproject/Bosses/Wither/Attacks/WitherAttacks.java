package survivaltweaks.infinitefunproject.Bosses.Wither.Attacks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.Bosses.Wither.InitWither.setWitherSpawn;

public class WitherAttacks implements Listener {

    /**
     * Wither picks attack when damaged
     *
     * @param event: Entity damage by entity event
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();

        if(damaged instanceof Wither) {
            Wither wither = (Wither) damaged;

            if(new Random().nextInt(0, 100) <= event.getFinalDamage() * 3 && wither.hasAI()) {
                pickAttack(wither);
            }
        }
    }

    /**
     * Wither pick attack
     *
     * @param wither: The attacking Wither
     */
    private void pickAttack(Wither wither) {
        int attack = new Random().nextInt(0, 9);

        double witherHpPercent = (wither.getHealth() / wither.getAttribute(Attribute.MAX_HEALTH).getValue()) * 100;

        wither.setGlowing(true);
        wither.getBossBar().setColor(BarColor.RED);
        wither.setCustomName(color("&cWither"));
        wither.setCustomNameVisible(true);

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {

            if(!wither.isDead()) {
                switch (attack) {
                    case 0:
                        superImplode(wither);
                        break;
                    case 1:
                        normalImplode(wither);
                        break;
                    case 2:
                        summonWitherSkeletons(wither);
                        break;
                    case 3:
                        skullBarrage(wither);
                        break;
                    case 4:
                        smokeScreen(wither);
                        break;
                    case 5:
                        launch(wither);
                        break;
                    case 6:
                        summonSkeletons(wither);
                        break;
                    case 7:
                        if(witherHpPercent >= 80) {
                            expandingBlast(wither);
                        } else {
                            normalImplode(wither);
                        }
                        break;
                    case 8:
                        clone(wither);
                        break;
                }
                wither.setGlowing(false);
                wither.getBossBar().setColor(BarColor.PURPLE);
                wither.setCustomName(color("&fWither"));
                wither.setCustomNameVisible(false);
            }
        }, 35);
    }

    /**
     * Wither clone attack
     *
     * @param wither: The attacking Wither
     */
    private void clone(Wither wither) {
        Wither clone = wither.getWorld().spawn(wither.getLocation(), Wither.class);
        clone.setHealth(wither.getHealth());
        clone.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 120, 4, false, false, false));

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(new Random().nextInt(0, 3) == 1) {
                wither.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20, 10, false,
                        false, false));
                clone.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20, 10, false,
                        false, false));
                clone.getWorld().createExplosion(clone.getLocation(), 9.8f, true, true, wither);
            }
            clone.remove();
        }, 160);
    }

    /**
     * Wither launch entities attack
     *
     * @param wither: The attacking Wither
     */
    private void launch(Wither wither) {
        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(8, 8, 8);

        wither.setVelocity(new Vector(0, 1.2, 0));
        wither.getWorld().spawnParticle(Particle.LARGE_SMOKE, wither.getLocation(), 70, .25, .25, .25, 0.2);

        for(Entity e : entities) {
            if(e instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e;
                entity.setVelocity(new Vector(0, 1.4, 0));
                entity.getWorld().spawnParticle(Particle.LARGE_SMOKE, entity.getLocation(), 70, .25, .25, .25, 0.2);
            }
        }
    }

    /**
     * Wither skull barrage attack
     *
     * @param wither: The attacking Wither
     */
    private void skullBarrage(Wither wither) {
        for(int i = 0; i < 10; i++) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> wither.launchProjectile(WitherSkull.class), i * 3);
        }
    }

    /**
     * Wither smoke screen attack
     *
     * @param wither: The attacking Wither
     */
    private void smokeScreen(Wither wither) {
        wither.getWorld().spawnParticle(Particle.LARGE_SMOKE, wither.getLocation(), 250, 3, 3, 3, 0.25);

        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(12, 12, 12);

        for(Entity e : entities) {
            if(e instanceof Player) {
                Player player = (Player) e;
                player.addPotionEffect(PotionEffectType.BLINDNESS.createEffect(120, 0));
            }
        }
    }

    /**
     * Wither summoning wither skeletons attack
     *
     * @param wither: The attacking Wither
     */
    private void summonWitherSkeletons(Wither wither) {
        for(int i = 0; i < 2; i++) {
            WitherSkeleton witherSkeleton = (WitherSkeleton) wither.getWorld().spawnEntity(wither.getLocation(), EntityType.WITHER_SKELETON);
            setWitherSpawn(witherSkeleton);

            witherSkeleton.setTarget(wither.getTarget());
        }
    }

    /**
     * Wither summoning skeletons attack
     *
     * @param wither: The attacking Wither
     */
    private void summonSkeletons(Wither wither) {
        for(int i = 0; i < 2; i++) {
            Skeleton skeleton = (Skeleton) wither.getWorld().spawnEntity(wither.getLocation(), EntityType.SKELETON);
            setWitherSpawn(skeleton);

            skeleton.setTarget(wither.getTarget());
        }
    }

    /**
     * Wither small explosion attack
     *
     * @param wither: The attacking Wither
     */
    private void normalImplode(Wither wither) {
        Location particleSpawn = wither.getLocation();
        particleSpawn.setY(particleSpawn.getY() + 0.95);

        wither.getWorld().spawnParticle(Particle.LARGE_SMOKE, particleSpawn, 8, 0.05 ,0.05, 0.05, 9);

        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(9, 9, 9);

        for(Entity e : entities) {
            if(e instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e;
                entity.damage(8, wither);
            }
        }
    }

    /**
     * Wither expanding explosion attack
     *
     * @param wither: The attacking Wither
     */
    private void expandingBlast(Wither wither) {
        wither.setAI(false);
        wither.teleport(wither.getLocation().add(new Vector(0, 5, 0)));

        new BukkitRunnable() {
            int radius = 3;
            @Override
            public void run() {
                if(wither.isDead()) {
                    cancel();
                    return;
                }
                if(wither.getLastDamage() >= (radius * 3)) {
                    cancel();
                    wither.setAI(true);
                    return;
                }

                if(wither.getHealth() < wither.getAttribute(Attribute.MAX_HEALTH).getValue() / 2) {
                    wither.setHealth((wither.getAttribute(Attribute.MAX_HEALTH).getValue() / 2) + 20);
                }

                Location particleSpawn = wither.getLocation();
                particleSpawn.setY(particleSpawn.getY() + 0.95);

                wither.getWorld().spawnParticle(Particle.EXPLOSION, particleSpawn, 8, 0.05 ,0.05, 0.05, radius);
                ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(radius, radius, radius);

                for(Entity e : entities) {
                    if(e instanceof LivingEntity) {
                        ((LivingEntity) e).damage(calculateBlastDamage(radius), wither);
                    }
                }

                if(radius <= 45) {
                    radius++;
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 14, 14);
    }

    /**
     * Calculate wither expanding blast damage
     *
     * @param blastRadius: The current radius
     */
    private double calculateBlastDamage(int blastRadius) {
        return evaluateLogIntegral(blastRadius) - evaluateLogIntegral(2.6);
    }

    /**
     * Evaluate integral of ln(x)
     * and divide by ln(10)
     *
     * x * ln(x) - x / ln(10)
     *
     * @param x: Number
     */
    private double evaluateLogIntegral(double x) {
        return (x * Math.log(x) - x) / Math.log(10);
    }

    /**
     * Wither explode and teleport forward attack
     *
     * @param wither: The attacking Wither
     */
    private void superImplode(Wither wither) {
        Location witherLoc = wither.getLocation();
        witherLoc.setY(witherLoc.getY() + 1);
        Vector direction = witherLoc.getDirection().normalize();

        for(int i = 0; i < 10; i++) {
            witherLoc = wither.getLocation();
            Location target = witherLoc.add(direction);
            target.setY(target.getY() - 1);
            Location oneAbove = target;
            oneAbove.setY(oneAbove.getY() + 1);

            if(!target.getBlock().getType().isSolid() && !oneAbove.getBlock().getType().isSolid()) {
                wither.teleport(target);
            } else {
                break;
            }
        }

        Location particleSpawn = wither.getLocation();
        particleSpawn.setY(particleSpawn.getY() + 0.95);

        wither.getWorld().spawnParticle(Particle.EXPLOSION, particleSpawn, 8, 0.05 ,0.05, 0.05, 9);

        ArrayList<Entity> entities = (ArrayList<Entity>) wither.getNearbyEntities(9, 9, 9);

        for(Entity e : entities) {
            if(e instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) e;
                entity.damage(8, wither);
            }
        }
    }
}
