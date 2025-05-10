package survivaltweaks.infinitefunproject.Player.Events;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BindArmor {

    public static void addBinding(Player player) {
        ItemStack[] armor = player.getInventory().getArmorContents();

        for(ItemStack i : armor) {

            if(i == null) {
                continue;
            }

            ItemMeta meta = i.getItemMeta();

            if(meta.hasEnchant(Enchantment.BINDING_CURSE)) {
                continue;
            } else {
                meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
                i.setItemMeta(meta);
            }
        }
    }
}
