package survivaltweaks.infinitefunproject.Mobs.Animals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.InfiniteFunProject;

import java.util.List;
import java.util.Random;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class DropBleachedFood implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent event) {
        if(new Random().nextInt(0, 25) == 1) {
            List<ItemStack> items = event.getDrops();

            for(ItemStack item : items) {
                if(item.getType().isEdible()) {
                    ItemMeta meta = item.getItemMeta();

                    meta.setDisplayName(ChatColor.GRAY + fixCaps(item.getType().toString()));
                    item.setItemMeta(meta);
                }
            }
        }
    }

    @EventHandler
    public void onSmelt(FurnaceSmeltEvent event) {
        ItemStack item = event.getResult();
        ItemStack original = event.getSource();

        if(original.hasItemMeta()) {
            if(original.getItemMeta().getDisplayName().equalsIgnoreCase(
                    ChatColor.GRAY + fixCaps(original.getType().toString()))) {
                ItemMeta meta = item.getItemMeta();

                meta.setDisplayName(ChatColor.GRAY + fixCaps(item.getType().toString()));
                item.setItemMeta(meta);
            }
        }
    }
}
