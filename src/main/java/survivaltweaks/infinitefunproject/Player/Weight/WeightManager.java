package survivaltweaks.infinitefunproject.Player.Weight;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WeightManager {

    public static double calculateWeight(Player player) {
        Inventory inventory = player.getInventory();
        double weight = 0;

        for(ItemStack item : inventory) {
            if(item == null) {
                continue;
            }

            if(item.getType().isBlock()) {
                weight += 8 * Math.max(item.getAmount() / 5, 1);

                if(!item.getType().isSolid()) {
                    weight -= 3 * Math.max(item.getAmount() / 5, 1);
                }
            }
            if(item.getMaxStackSize() == 1) {
                weight += 8;
            }

            if(translationKeyContains(item, "glass")) {
                weight -= 4 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "bucket")) {
                weight += 6 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "gold")) {
                weight += 50 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "iron")) {
                weight += 24 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "netherite")) {
                weight += 30 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "anvil")) {
                weight += 90 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "axe")) {
                weight += 20;
            }
            if(translationKeyContains(item, "chestplate")) {
                weight += 16;
            }
            if(translationKeyContains(item, "leggings")) {
                weight += 11;
            }
            if(item.getMaxStackSize() >= 16) {
                weight -= 2 * Math.max(item.getAmount() / 5, 1);
            }
            if(translationKeyContains(item, "nugget")) {
                weight -= 8 * Math.max(item.getAmount() / 5, 1);
            }

            for(Enchantment enchantment : item.getEnchantments().keySet()) {
                weight += item.getEnchantmentLevel(enchantment);
            }

            weight += (item.getAmount() * 2) + 3;
        }

        if(player.isInWater() || !player.getWorld().isClearWeather()) {
            weight *= 1.5;
        }

        return Math.max(weight, 0);
    }

    public static boolean translationKeyContains(ItemStack item, String str) {
        if(item.getType().getItemTranslationKey() != null) {
            return item.getType().getItemTranslationKey().contains(str);
        }
        if(item.getType().getBlockTranslationKey() != null) {
            return item.getType().getBlockTranslationKey().contains(str);
        }

        return false;
    }

    public static void applyWeightEffects(Player player) {
        double weight = calculateWeight(player);

        if(weight >= 350) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 0, false, false, false));
        }
        if(weight >= 1000) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1, false, false, false));
        }
        if(weight >= 1750) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 2, false, false, false));
        }
        if(weight >= 2400) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 0, false, false, false));
        }
        if(weight >= 3000) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 1, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0, false, false, false));
        }
        if(weight >= 4200) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 2, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 1, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0, false, false, false));
        }
        if(weight >= 5300) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 3, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 2, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0, false, false, false));
        }
        if(weight >= 6500) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 4, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30, 4, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 30, 4, false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 30, 0, false, false, false));
        }
    }
}
