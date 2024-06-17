package survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passives;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.Passive;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility;

import static survivaltweaks.infinitefunproject.CustomItems.Abilities.Passive.PassiveAbility.hasAbility;

public class AquaticSustain implements Passive {
    @Override
    public void activate(Player player) {
        if(player.isInWater()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20, 0,
                    false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 20, 0,
                    false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20, 0,
                    false, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 0,
                    false, false, false));

            ItemStack mainHand = player.getInventory().getItemInMainHand();

            if(hasAbility(mainHand, PassiveAbility.AQUATIC_ADAPTATION)) {
                ItemMeta meta = mainHand.getItemMeta();

                if(meta.hasEnchant(Enchantment.LOYALTY)) {
                    meta.removeEnchant(Enchantment.LOYALTY);
                }

                if(!meta.hasEnchant(Enchantment.RIPTIDE)) {
                    meta.addEnchant(Enchantment.RIPTIDE, 3, true);
                }
                mainHand.setItemMeta(meta);
            }
        } else {
            ItemStack mainHand = player.getInventory().getItemInMainHand();

            if(hasAbility(mainHand, PassiveAbility.AQUATIC_ADAPTATION)) {
                ItemMeta meta = mainHand.getItemMeta();

                if(meta.hasEnchant(Enchantment.RIPTIDE)) {
                    meta.removeEnchant(Enchantment.RIPTIDE);
                }

                if(!meta.hasEnchant(Enchantment.LOYALTY)) {
                    meta.addEnchant(Enchantment.LOYALTY, 4, true);
                }
                mainHand.setItemMeta(meta);
            }
        }
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "A powerful adaptation for both\nwater and land.";
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
