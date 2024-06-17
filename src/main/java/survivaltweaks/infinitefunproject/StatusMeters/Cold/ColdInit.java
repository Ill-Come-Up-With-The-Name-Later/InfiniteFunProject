package survivaltweaks.infinitefunproject.StatusMeters.Cold;

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

public class ColdInit {
    public static HashMap<Player, Integer> playerCold = new HashMap<>();

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new ResetColdMeter(), InfiniteFunProject.plugin);
    }

    public static void setupCold(Player player) {
        ArrayList<Biome> coldBiomes = new ArrayList<>();
        coldBiomes.add(Biome.SNOWY_BEACH);
        coldBiomes.add(Biome.SNOWY_PLAINS);
        coldBiomes.add(Biome.SNOWY_SLOPES);
        coldBiomes.add(Biome.SNOWY_TAIGA);
        coldBiomes.add(Biome.ICE_SPIKES);
        coldBiomes.add(Biome.DEEP_COLD_OCEAN);
        coldBiomes.add(Biome.FROZEN_OCEAN);
        coldBiomes.add(Biome.FROZEN_PEAKS);
        coldBiomes.add(Biome.FROZEN_RIVER);
        coldBiomes.add(Biome.DEEP_FROZEN_OCEAN);
        coldBiomes.add(Biome.COLD_OCEAN);

        setCold(player, playerCold.getOrDefault(player, 0));
        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(coldBiomes.contains(player.getWorld().getBiome(player.getLocation()))) {
                if(getCold(player) > -1) {
                    if(WorldModInit.modifierActive(WorldModifier.TOUGHENED_ELEMENTS)) {
                        setCold(player, Math.min(getCold(player) + 2, 100));
                    } else {
                        setCold(player, Math.min(getCold(player) + 1, 100));
                    }
                }
            } else {
                if(getCold(player) > -1) {
                    if(player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                        setCold(player, Math.max(getCold(player) - 3, 0));
                    } else {
                        setCold(player, Math.max(getCold(player) - 1, 0));
                    }
                }
            }
        }, 600, 600);

        Bukkit.getScheduler().runTaskTimer(InfiniteFunProject.plugin, () -> {
            if(getCold(player) >= 70 && getCold(player) <= 80) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, true, true, true));
            }
            if(getCold(player) > 80 && getCold(player) <= 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, true, true, true));
            }
            if(getCold(player) > 95) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1, true, true, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 0, true, true, true));
            }
        }, 1, 1);
    }

    public static int getCold(Player player) {
        if(playerCold.containsKey(player)) {
            return playerCold.get(player);
        }
        return -1;
    }

    public static void setCold(Player player, int i) {
        if(playerCold.containsKey(player)) {
            playerCold.remove(player);
            playerCold.put(player, i);
        } else {
            playerCold.put(player, i);
        }
    }
}
