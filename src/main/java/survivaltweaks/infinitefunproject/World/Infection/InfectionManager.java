package survivaltweaks.infinitefunproject.World.Infection;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Mobs.Bat.InfectedMetadata;

import java.util.ArrayList;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.circularNearbyEntities;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class InfectionManager {

    public static float infectionRadius = 5.5f;
    public static int infectionDuration = 3600;

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveInfection(), InfiniteFunProject.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new DeathMessage(), InfiniteFunProject.plugin);

        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            for(World world : Bukkit.getWorlds()) {
                for(Entity entity : world.getEntities()) {
                    if(entity instanceof LivingEntity) {
                        LivingEntity living = (LivingEntity) entity;

                        if(living.hasMetadata("Infected") && !entity.isDead()) {
                            drawCircle(living.getLocation(), infectionRadius, Particle.VILLAGER_HAPPY, 90);
                            infectSurrounding(living);
                        }
                    }
                }
            }
        }, 4, 4);
    }

    public static void infectSurrounding(LivingEntity entity) {
        ArrayList<Entity> entities = (ArrayList<Entity>) circularNearbyEntities(entity, infectionRadius);

        for(Entity e : entities) {
            if(e instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) e;

                if(!living.hasMetadata("Infected")) {
                    infectEntity(living);
                }
            }
        }
    }

    public static void infectEntity(LivingEntity entity) {
        if(entity instanceof Player) {
            Player player = (Player) entity;

            if(player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) {
                return;
            }
        }

        if(entity.isDead()) {
            return;
        }

        if(entity.hasMetadata("Immune")) {
            return;
        }

        entity.setMetadata("Infected", new InfectedMetadata());
        entity.sendMessage(ChatColor.LIGHT_PURPLE + "You are now infected with Coronavirus!");

        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, infectionDuration, 1, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, infectionDuration, 1, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, infectionDuration, 1, false, false, false));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, infectionDuration, 1, false, false, false));

        if(new Random().nextInt(0, 12) == 4) {
            Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
                entity.damage(10000);
                entity.sendMessage(ChatColor.RED + "You died from the Coronavirus!");
            }, new Random().nextInt(1000, 3401));
        }

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            entity.removeMetadata("Infected", InfiniteFunProject.plugin);
            entity.sendMessage(ChatColor.GOLD + "You are no longer infected!");

            grantImmunity(entity, 4800);
        }, infectionDuration);
    }

    public static void disinfectAll() {
        for(World world : Bukkit.getWorlds()) {
            for(LivingEntity entity : world.getLivingEntities()) {
                if(entity.hasMetadata("Infected")) {
                    entity.removeMetadata("Infected", InfiniteFunProject.plugin);
                }

                if(entity.hasMetadata("Cancer")) {
                    entity.removeMetadata("Cancer", InfiniteFunProject.plugin);
                }
            }
        }
    }

    public static void grantImmunity(LivingEntity entity, int duration) {
        if(entity.hasMetadata("Infected")) {
            entity.removeMetadata("Infected", InfiniteFunProject.plugin);
        }

        entity.setMetadata("Immune", new ImmuneMetadata());
        entity.sendMessage(ChatColor.GREEN + "You are temporarily immune to the Coronavirus");

        Bukkit.getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            entity.removeMetadata("Immune", InfiniteFunProject.plugin);
            entity.sendMessage(ChatColor.YELLOW + "You are no longer immune to the Coronavirus");
        }, duration);
    }

    public static void grantImmunity(LivingEntity entity) {
        if(entity.hasMetadata("Infected")) {
            entity.removeMetadata("Infected", InfiniteFunProject.plugin);
        }

        entity.setMetadata("Immune", new ImmuneMetadata());
        entity.sendMessage(ChatColor.GREEN + "You are permanently immune to the Coronavirus");
    }
}
