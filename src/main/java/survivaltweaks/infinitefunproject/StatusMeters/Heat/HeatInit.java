package survivaltweaks.infinitefunproject.StatusMeters.Heat;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.getThirst;
import static survivaltweaks.infinitefunproject.StatusMeters.Thirst.ThirstInit.setThirst;

public class HeatInit {
    public static HashMap<Player, Integer> playerHeat = new HashMap<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ResetHeatMeter(), InfiniteFunProject.plugin);
    }

    public static void setupHeat(Player player) {
        ArrayList<Biome> hotBiomes = new ArrayList<>();
        hotBiomes.add(Biome.DESERT);
        hotBiomes.add(Biome.BADLANDS);
        hotBiomes.add(Biome.ERODED_BADLANDS);
        hotBiomes.add(Biome.WOODED_BADLANDS);
        hotBiomes.add(Biome.BEACH);
        hotBiomes.add(Biome.JUNGLE);
        hotBiomes.add(Biome.BAMBOO_JUNGLE);
        hotBiomes.add(Biome.SPARSE_JUNGLE);

        setHeat(player, playerHeat.getOrDefault(player, 0));
        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(player.getWorld().getEnvironment().equals(World.Environment.NETHER) ||
                    hotBiomes.contains(player.getWorld().getBiome(player.getLocation()))) {
                if(getHeat(player) > -1) {
                    if(WorldModInit.modifierActive(WorldModifier.TOUGHENED_ELEMENTS)) {
                        setHeat(player, Math.min(getHeat(player) + 2, 100));
                    } else {
                        setHeat(player, Math.min(getHeat(player) + 1, 100));
                    }
                    if(new Random().nextInt(0, 3) == 1) {
                        setThirst(player, Math.min(getThirst(player) + 1, 100));
                    }
                }
            } else {
                if(getHeat(player) > -1) {
                    setHeat(player, Math.max(getHeat(player) -2, 0));
                }
            }
        }, 800, 800);

        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(getHeat(player) >= 70 && getHeat(player) <= 80) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, true, true, true));
            }
            if(getHeat(player) > 80 && getHeat(player) <= 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, true, true, true));
            }
            if(getHeat(player) > 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, true, true, true));
            }
        }, 1, 1);
    }

    public static int getHeat(Player player) {
        if(playerHeat.containsKey(player)) {
            return playerHeat.get(player);
        }
        return -1;
    }

    public static void setHeat(Player player, int i) {
        if(playerHeat.containsKey(player)) {
            playerHeat.remove(player);
            playerHeat.put(player, i);
        } else {
            playerHeat.put(player, i);
        }
    }
}
