package survivaltweaks.infinitefunproject.ChallengeMode.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class CleanItemListener implements Listener {

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        Player player = event.getEnchanter();

        if(player.hasMetadata("Clean Items")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent event) {
        Player player = (Player) event.getView().getPlayer();

        if(player.hasMetadata("Clean Items")) {
            event.setResult(new ItemStack(Material.AIR));
        }
    }
}
