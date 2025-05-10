package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.ItemSelector;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class UnboxCustomItem implements ActivatedAbility, Listener {

    private final String title = "Special Crate Loot";

    @Override
    public void activate(Player player) {
        player.sendMessage(ChatColor.GREEN + "Unboxing your loot!");

        Inventory inventory = Bukkit.createInventory(player, 9, title);
        ArrayList<ItemStack> loot = getLootItems();

        switch(loot.size()) {
            case 1:
                inventory.setItem(4, loot.get(0));
                break;
            case 2:
                inventory.setItem(2, loot.get(0));
                inventory.setItem(6, loot.get(1));
                break;
            case 3:
                inventory.setItem(1, loot.get(0));
                inventory.setItem(4, loot.get(1));
                inventory.setItem(7, loot.get(2));
                break;
        }

        player.openInventory(inventory);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        InventoryView view = event.getView();

        if(inventory == null) {
            return;
        }

        if(inventory.equals(view.getTopInventory())) {
            if(view.getTitle().equals(title)) {
                event.setCancelled(true);

                if(item == null) {
                    return;
                }

                player.getInventory().addItem(item);
                inventory.remove(item);

                if(inventory.isEmpty()) {
                    inventory.close();
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();
        InventoryView view = event.getView();

        if(view.getTitle().equals(title)) {
            if(inventory.equals(view.getTopInventory())) {
                for(int i = 0; i < inventory.getSize(); i++) {
                    if(inventory.getItem(i) == null) {
                        continue;
                    }

                    player.getInventory().addItem(Objects.requireNonNull(inventory.getItem(i)));
                }
            }
        }
    }

    private ArrayList<ItemStack> getLootItems() {
        ArrayList<ItemStack> drops = new ArrayList<>();
        int numItems = 1;

        if(new Random().nextInt(0, 10) == 1) {
            numItems++;
        }

        if(new Random().nextInt(0, 20) == 1) {
            numItems++;
        }

        ItemSelector<ItemStack> crateItems = new ItemSelector<>();

        for(ItemStack item : ItemManager.customItems) {
            crateItems.addEntry(item, 1);
        }

        for(int i = 0; i < numItems; i++) {
            ItemStack drop = new ItemStack(crateItems.rollItem());
            drops.add(drop);
        }

        return drops;
    }

    @Override
    public int getCooldown() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Opens the crate.";
    }

    @Override
    public boolean oneTimeUse() {
        return true;
    }

    @Override
    public boolean cooldownModifiable() {
        return false;
    }
}
