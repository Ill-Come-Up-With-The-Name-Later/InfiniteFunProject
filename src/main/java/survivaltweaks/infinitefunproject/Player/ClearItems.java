package survivaltweaks.infinitefunproject.Player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.ArrayList;

public class ClearItems {

    public static ArrayList<Material> clearedItems;
    public static ArrayList<PotionEffectType> clearedEffects;

    public static void init() {
        clearedItems = new ArrayList<>();
        clearedEffects = new ArrayList<>();

        clearedItems.add(Material.TOTEM_OF_UNDYING);

        clearedEffects.add(PotionEffectType.FIRE_RESISTANCE);

        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    clearItems(player);
                    clearEffects(player);
                }
            }
        }.runTaskTimer(InfiniteFunProject.plugin, 1, 1);
    }

    public static void clearItems(Player player) {
        for(Material material : clearedItems) {
            if(player.getInventory().contains(material)) {
                player.getInventory().remove(material);
            }
        }
    }

    public static void clearEffects(Player player) {
        for(PotionEffectType effectType : clearedEffects) {
            if(player.hasPotionEffect(effectType)) {
                player.removePotionEffect(effectType);;
            }
        }
    }
}
