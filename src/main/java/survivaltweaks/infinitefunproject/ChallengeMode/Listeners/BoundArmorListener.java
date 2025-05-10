package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BoundArmorListener implements Listener {

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event) {
        Player player = event.getPlayer();
        ItemStack[] armor = player.getInventory().getArmorContents();
        if (player.hasMetadata("Stuck to You")) {
            for(ItemStack i : armor) {
                if(i == null) {
                    continue;
                }

                ItemMeta meta = i.getItemMeta();

                if(meta.hasEnchant(Enchantment.BINDING_CURSE)) {
                    continue;
                } else {
                    meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);

                    meta.setUnbreakable(true);
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

                    i.setItemMeta(meta);
                }
            }
        }
    }
}
