package survivaltweaks.infinitefunproject.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.Bosses.EnderDragon.InitDragon;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;
import static survivaltweaks.infinitefunproject.Periodic.Events.Anomalies.MakePlayersInvincible.grantInvulnerability;

public class OnSpawn implements Listener {

    public static ArrayList<EntityType> levelers = new ArrayList<>();

    public static int maxLevel = 200;

    public static void createList() {
        levelers.add(EntityType.BLAZE);
        levelers.add(EntityType.WITHER_SKELETON);
        levelers.add(EntityType.WITCH);
        levelers.add(EntityType.ZOMBIE);
        levelers.add(EntityType.DROWNED);
        levelers.add(EntityType.CREEPER);
        levelers.add(EntityType.HUSK);
        levelers.add(EntityType.SKELETON);
        levelers.add(EntityType.STRAY);
        levelers.add(EntityType.PHANTOM);
        levelers.add(EntityType.SLIME);
        levelers.add(EntityType.MAGMA_CUBE);
        levelers.add(EntityType.EVOKER);
        levelers.add(EntityType.VINDICATOR);
        levelers.add(EntityType.VEX);
        levelers.add(EntityType.CAVE_SPIDER);
        levelers.add(EntityType.SPIDER);
        levelers.add(EntityType.PIGLIN_BRUTE);
        levelers.add(EntityType.PIGLIN);
        levelers.add(EntityType.ZOMBIFIED_PIGLIN);
        levelers.add(EntityType.ZOMBIE_VILLAGER);
        levelers.add(EntityType.HOGLIN);
        levelers.add(EntityType.ZOGLIN);
        levelers.add(EntityType.PILLAGER);
        levelers.add(EntityType.ENDERMAN);
        levelers.add(EntityType.SILVERFISH);
        levelers.add(EntityType.ENDERMITE);
        levelers.add(EntityType.SHULKER);
        levelers.add(EntityType.ILLUSIONER);
        levelers.add(EntityType.RAVAGER);
        levelers.add(EntityType.BREEZE);
        levelers.add(EntityType.ELDER_GUARDIAN);
        levelers.add(EntityType.GUARDIAN);
        levelers.add(EntityType.BOGGED);
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        Entity e = event.getEntity();

        if(e instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) e;

            grantInvulnerability(livingEntity, 6);
        }

        int finalLevel = getLevel(e);
        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            if(e instanceof Enemy && !(e instanceof Boss) && !e.hasMetadata("NoLevel") && !e.hasMetadata("WitherSpawn")) {
                LivingEntity entity = (LivingEntity) e;
                entity.getAttribute(Attribute.MAX_HEALTH).setBaseValue((entity.getAttribute(Attribute.MAX_HEALTH).getValue() * 0.8)
                        + (finalLevel * 3));
                entity.setHealth(entity.getAttribute(Attribute.MAX_HEALTH).getValue());
                entity.getAttribute(Attribute.ATTACK_DAMAGE).
                        setBaseValue(entity.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue() * 1 + (finalLevel * 0.75));
                if (finalLevel >= 10) {
                    entity.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(-1, 0));
                }

                entity.setCustomName(color("&7[&rLevel &b" + finalLevel + "&7] &e" + fixCaps(entity.getType().toString())));
                entity.setPersistent(false);

                if(InitDragon.dragonDead()) {
                    entity.setCustomName(null);
                    entity.getAttribute(Attribute.MAX_HEALTH).setBaseValue(entity.getAttribute(Attribute.MAX_HEALTH).getValue() * 1.66);
                    entity.setHealth(entity.getAttribute(Attribute.MAX_HEALTH).getValue());
                    entity.getAttribute(Attribute.ATTACK_DAMAGE).
                            setBaseValue(entity.getAttribute(Attribute.ATTACK_DAMAGE).getBaseValue() * 1.5);
                    entity.setCustomName(color("&6[&rLevel &b" + finalLevel + "&6] &e" + fixCaps(e.getType().toString())));
                }

                if(entity.getHealth() > 2500) {
                    entity.remove();
                }

                Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                    if(entity instanceof Ageable) {
                        Ageable ageable = (Ageable) entity;
                        if(!ageable.isAdult()) {
                            ageable.getAttribute(Attribute.MAX_HEALTH).
                                    setBaseValue(ageable.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.75);
                            ageable.setHealth(ageable.getAttribute(Attribute.MAX_HEALTH).getBaseValue());
                        }
                    }
                }, 1);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(entity.getHealth() <= 0) {
                            entity.remove();
                        }
                    }
                }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
            }
        }, 2);
    }

    public static int getLevel(Entity e) {
        long day = e.getWorld().getFullTime() / 24000;

        int level = (int) Math.min(Math.floor((double) day / 8), maxLevel);

        if (level == 0) {
            level += 1;
        }

        return level;
    }

    public static int getLevel() {
        long day = Bukkit.getWorlds().get(0).getFullTime() / 24000;

        int level = (int) Math.min(Math.floor((double) day / 8), maxLevel);

        if (level == 0) {
            level += 1;
        }

        return level;
    }

    public static int getMaxLevel() {
        return maxLevel;
    }

    public static ArrayList<EntityType> getLevelers() {
        return levelers;
    }
}

