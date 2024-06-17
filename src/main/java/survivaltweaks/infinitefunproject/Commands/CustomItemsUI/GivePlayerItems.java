package survivaltweaks.infinitefunproject.Commands.CustomItemsUI;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class GivePlayerItems implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        Inventory top = event.getView().getTopInventory();
        Inventory clicked = event.getClickedInventory();
        String title = event.getView().getTitle();

        if(item == null) {
            return;
        }
        if(title.equals("Custom Items")) {
            event.setCancelled(true);
        }
        if(clicked.equals(top) && title.equals("Custom Items") && !item.equals(ItemManager.comingSoon)) {
            player.getInventory().addItem(item.clone());
            player.sendMessage(ChatColor.GREEN + "Gave you " + ChatColor.RESET +
                    item.getItemMeta().getDisplayName() + ChatColor.GREEN + ".");
        }
    }
}
