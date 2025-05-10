package survivaltweaks.infinitefunproject.StatusMeters.Thirst;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.HashMap;

import static survivaltweaks.infinitefunproject.StatusMeters.Cold.ColdInit.getCold;
import static survivaltweaks.infinitefunproject.StatusMeters.Heat.HeatInit.getHeat;

public class ThirstInit {
    public static HashMap<Player, Integer> playerThirst = new HashMap<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ResetThirstMeter(), InfiniteFunProject.plugin);
    }

    public static void setupThirst(Player player) {
        setThirst(player, playerThirst.getOrDefault(player, 0));
        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(getThirst(player) > -1) {
                if(WorldModInit.modifierActive(WorldModifier.TOUGHENED_ELEMENTS)) {
                    setThirst(player, Math.min(getThirst(player) + 2, 100));
                } else {
                    setThirst(player, Math.min(getThirst(player) + 1, 100));
                }
            }
        }, 1200, 1200);

        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(getThirst(player) >= 70 && getThirst(player) <= 80) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, true, true, true));
            }
            if(getThirst(player) > 80 && getThirst(player) <= 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 0, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1, true, true, true));
            }
            if(getThirst(player) > 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, true, true, true));
            }
        }, 1, 1);
    }

    public static int getThirst(Player player) {
        if(playerThirst.containsKey(player)) {
            return playerThirst.get(player);
        }
        return -1;
    }

    public static void setThirst(Player player, int i) {
        if(playerThirst.containsKey(player)) {
            playerThirst.remove(player);
            playerThirst.put(player, i);
        } else {
            playerThirst.put(player, i);
        }
    }
}
