package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.replaceUnusual;

public class SetUnusual implements ActivatedAbility, Listener {

    private final String title = "Set Effect: Page: 1";
    public final Material iconMaterial = Material.MAGENTA_DYE;

    @Override
    public void activate(Player player) {
        Inventory inventory = Bukkit.createInventory(player, 54, title);
        generateInventory(1, inventory);

        player.openInventory(inventory);
    }

    public void generateInventory(int page, Inventory inventory) {
        int index = 0;
        int unusualIndex = ((page - 1) * 52);

        inventory.clear();

        if(unusualIndex >= Unusual.values().length) {
            inventory.setItem(45, ItemManager.backArrow);
            inventory.setItem(53, ItemManager.forwardArrow);

            return;
        }

        for(int i = 0; i < inventory.getSize(); i++) {
            if(index == 45 || index == 53) {
                index++;
                continue;
            }

            if(unusualIndex >= Unusual.values().length - 1) {
                break;
            }

            ItemStack icon = new ItemStack(iconMaterial);

            ItemMeta iconMeta = icon.getItemMeta();
            iconMeta.setDisplayName(ChatColor.LIGHT_PURPLE + Unusual.values()[unusualIndex].getName());

            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.GREEN + "Click to set effect" + ChatColor.GRAY + ":");
            lore.add(ChatColor.GRAY + "- " + ChatColor.DARK_PURPLE + Unusual.values()[unusualIndex].getName());

            iconMeta.setLore(lore);
            icon.setItemMeta(iconMeta);

            inventory.setItem(index, icon);
            index++;
            unusualIndex++;
        }

        inventory.setItem(45, ItemManager.backArrow);
        inventory.setItem(53, ItemManager.forwardArrow);
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Select an effect for this\nUnusualifier to grant.";
    }

    @Override
    public boolean oneTimeUse() {
        return false;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        InventoryView view = event.getView();
        String invTitle = view.getTitle();
        ItemStack item = event.getCurrentItem();
        ItemStack playerHand = player.getInventory().getItemInMainHand();

        if(item == null) {
            return;
        }

        if(inventory == null) {
            return;
        }

        if(invTitle.contains(title.substring(0, title.length() - 2))) {
            event.setCancelled(true);

            if(inventory.equals(view.getTopInventory()) && item.getType().equals(iconMaterial)) {
                Unusual effect = Unusual.getByName(item.getItemMeta().getDisplayName().substring(2));

                replaceUnusual(playerHand, effect);
                inventory.close();

                player.sendMessage(ChatColor.GREEN + "Set effect to " + ChatColor.DARK_PURPLE + effect.getName() + ChatColor.GREEN + ".");
            }

            if(inventory.equals(view.getTopInventory()) && item.equals(ItemManager.backArrow)) {
                int page = Integer.parseInt(invTitle.split(" ")[invTitle.split(" ").length - 1]);

                if(page - 1 == 0) {
                    return;
                }

                view.setTitle("Set Effect: Page: " + (page - 1));
                generateInventory(page - 1, view.getTopInventory());
            }

            if(inventory.equals(view.getTopInventory()) && item.equals(ItemManager.forwardArrow)) {
                int page = Integer.parseInt(invTitle.split(" ")[invTitle.split(" ").length - 1]);

                view.setTitle("Set Effect: Page: " + (page + 1));
                generateInventory(page + 1, view.getTopInventory());
            }
        }
    }
}
