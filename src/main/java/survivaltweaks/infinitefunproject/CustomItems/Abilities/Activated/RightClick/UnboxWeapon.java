package survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.RightClick;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import survivaltweaks.infinitefunproject.CustomItems.Abilities.Activated.ActivatedAbility;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.ItemSelector;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.hasUnusual;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.drawCircle;

public class UnboxWeapon implements ActivatedAbility, Listener {

    private final String title = "Weapon Crate Loot";

    @Override
    public void activate(Player player) {
        player.sendMessage(ChatColor.GREEN + "Unboxing your loot!");

        Inventory inventory = Bukkit.createInventory(player, 9, title);
        ArrayList<ItemStack> loot = getLootItems();

        for(ItemStack item : loot) {
            if(hasUnusual(item)) {
                drawCircle(player.getLocation(), 1.2, Particle.WITCH, 90);
                Bukkit.spigot().broadcast(new TextComponent(ChatColor.GREEN + player.getName() + " just unboxed an unusual!"));
            }
        }

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

        ItemSelector<Material> crateItems = new ItemSelector<>();

        crateItems.addEntry(Material.WOODEN_SWORD, 10);
        crateItems.addEntry(Material.WOODEN_AXE, 10);
        crateItems.addEntry(Material.STONE_SWORD, 9);
        crateItems.addEntry(Material.STONE_AXE, 9);
        crateItems.addEntry(Material.GOLDEN_SWORD, 7);
        crateItems.addEntry(Material.GOLDEN_AXE, 7);
        crateItems.addEntry(Material.IRON_SWORD, 8);
        crateItems.addEntry(Material.IRON_AXE, 8);
        crateItems.addEntry(Material.DIAMOND_SWORD, 3);
        crateItems.addEntry(Material.DIAMOND_AXE, 3);
        crateItems.addEntry(Material.NETHERITE_SWORD, 1);
        crateItems.addEntry(Material.NETHERITE_AXE, 2);

        crateItems.addEntry(Material.BOW, 3);
        crateItems.addEntry(Material.CROSSBOW, 3);
        crateItems.addEntry(Material.TRIDENT, 2);
        crateItems.addEntry(Material.MACE, 1);

        int chance = 100;

        if(WorldModInit.modifierActive(WorldModifier.LUCK_MANIPULATION)) {
            chance /= 2;
        }

        for(int i = 0; i < numItems; i++) {
            ItemStack drop = new ItemStack(crateItems.rollItem());

            if(new Random().nextInt(0, chance) == 1) {
                Unusual.addRandomUnusual(drop);
            }

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
