package survivaltweaks.infinitefunproject.CustomItems.Unusual;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.Optional;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.allowedItems;
import static survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager.unusualLine;

public class SpecialUnusualifier implements Listener {

    @EventHandler
    public void apply(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack chosen = event.getCurrentItem();
        ItemStack cursorItem = player.getItemOnCursor();
        InventoryAction action = event.getAction();

        if(!cursorItem.hasItemMeta()) {
            return;
        }

        if(!cursorItem.getItemMeta().hasDisplayName()) {
            return;
        }

        if(cursorItem.getItemMeta().getDisplayName().equals(ItemManager.specialUnusualifier.getItemMeta().getDisplayName())
                && allowedItems.contains(chosen.getType()) && action == InventoryAction.SWAP_WITH_CURSOR) {
            event.setCancelled(true);

            int unusualLine = unusualLine(cursorItem);

            if (unusualLine == -1) {
                return;
            }

            String effect = cursorItem.getItemMeta().getLore().get(unusualLine).substring(24);
            Optional<Unusual> unusualOptional = Optional.ofNullable(Unusual.getByName(effect));

            if(unusualOptional.isPresent()) {
                if(hasUnusual(chosen)) {
                    Unusual.replaceUnusual(chosen, unusualOptional.get());
                } else {
                    Unusual.addUnusual(chosen, unusualOptional.get());
                }
                player.setItemOnCursor(new ItemStack(Material.AIR));
            }
        }
    }
}
