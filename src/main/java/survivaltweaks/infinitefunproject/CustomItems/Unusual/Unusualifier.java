package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.replaceUnusual;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.allowedItems;

public class Unusualifier implements Listener {

    @EventHandler
    public void apply(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack chosen = event.getCurrentItem();
        ItemStack cursorItem = player.getItemOnCursor();
        InventoryAction action = event.getAction();

        if(cursorItem.equals(ItemManager.unusualifier) && allowedItems.contains(chosen.getType()) && action == InventoryAction.SWAP_WITH_CURSOR) {
            event.setCancelled(true);

            if(hasUnusual(chosen)) {
                Unusual.replaceUnusual(chosen, Unusual.rollRandomEffect());
            } else {
                Unusual.addRandomUnusual(chosen);
            }
            player.setItemOnCursor(new ItemStack(Material.AIR));
        }
    }
}
