package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PreventAccidents implements Listener {

    @EventHandler
    public static void onPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType() == Material.AIR) {
            return;
        }

        if(RCAbility.hasAbility(item) || LCAbility.hasAbility(item)) {
            event.setCancelled(true);
            event.setBuild(false);
        }
    }

    /*
    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = event.getAction();

        if(item.getType() == Material.AIR) {
            return;
        }

        if(action.isLeftClick()) {
            if(RCAbility.hasAbility(item)) {
                event.setCancelled(true);
            }
        }
        if(action.isRightClick()) {
            if(LCAbility.hasAbility(item)) {
                event.setCancelled(true);
            }
        }
    }
     */
}
