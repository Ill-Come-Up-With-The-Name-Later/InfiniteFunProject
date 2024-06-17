package survivaltweaks.infinitefunproject.Player.Marriage;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.HashMap;

public class MarriageManager {

    public static HashMap<LivingEntity, LivingEntity> marriages = new HashMap<>();

    public static void init() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(LivingEntity entity : marriages.keySet()) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0,
                            false, false, false));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0,
                            false, false, false));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0,
                            false, false, false));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0,
                            false, false, false));

                    if(marriages.get(entity).isDead()) {
                        divorce(entity);
                        entity.sendMessage(ChatColor.RED + "Your spouse is dead.");
                        continue;
                    }

                    marriages.get(entity).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0,
                            false, false, false));
                    marriages.get(entity).addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0,
                            false, false, false));
                    marriages.get(entity).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0,
                            false, false, false));
                    marriages.get(entity).addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0,
                            false, false, false));
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    public static void marry(LivingEntity entity, LivingEntity entity2) {
        if(marriages.containsKey(entity)) {
            divorce(entity);
        }

        marriages.put(entity, entity2);
        entity.sendMessage(ChatColor.GREEN + "You are now married!");
        entity2.sendMessage(ChatColor.GREEN + "You are now married!");
    }

    public static LivingEntity getSpouse(LivingEntity entity) {
        if(marriages.containsKey(entity)) {
            return marriages.get(entity);
        }
        if(marriages.containsValue(entity)) {
            for(LivingEntity e : marriages.keySet()) {
                if(marriages.get(e).equals(entity)) {
                    return e;
                }
            }
        }

        return null;
    }

    public static void divorce(LivingEntity entity) {
        if(marriages.containsKey(entity)) {
            marriages.remove(entity);
            entity.sendMessage(ChatColor.YELLOW + "You are now divorced!");
        }
        if(marriages.containsValue(entity)) {
            for(LivingEntity e : marriages.keySet()) {
                if(marriages.get(e).equals(entity)) {
                    marriages.remove(e);
                    entity.sendMessage(ChatColor.YELLOW + "You are now divorced!");
                }
            }
        }
    }
}
