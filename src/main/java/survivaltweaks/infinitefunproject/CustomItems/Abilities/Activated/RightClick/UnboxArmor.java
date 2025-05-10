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

public class UnboxArmor implements ActivatedAbility, Listener {

    private final String title = "Wearable Crate Loot";

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

        crateItems.addEntry(Material.NETHERITE_BOOTS, 1);
        crateItems.addEntry(Material.NETHERITE_HELMET, 1);
        crateItems.addEntry(Material.DIAMOND_BOOTS, 5);
        crateItems.addEntry(Material.DIAMOND_HELMET, 5);
        crateItems.addEntry(Material.IRON_BOOTS, 8);
        crateItems.addEntry(Material.IRON_HELMET, 8);
        crateItems.addEntry(Material.GOLDEN_BOOTS, 11);
        crateItems.addEntry(Material.GOLDEN_HELMET, 11);
        crateItems.addEntry(Material.CHAINMAIL_BOOTS, 14);
        crateItems.addEntry(Material.CHAINMAIL_HELMET, 14);
        crateItems.addEntry(Material.LEATHER_BOOTS, 80);
        crateItems.addEntry(Material.LEATHER_HELMET, 80);

        int chance = 100;

        if(WorldModInit.modifierActive(WorldModifier.LUCK_MANIPULATION)) {
            chance /= 2;
        }

        for(int i = 0; i < numItems; i++) {
            ItemStack drop = new ItemStack(crateItems.rollItem());

            if(drop.getType() == Material.LEATHER_BOOTS || drop.getType() == Material.LEATHER_HELMET) {
                LeatherArmorMeta meta = (LeatherArmorMeta) drop.getItemMeta();

                meta.setColor(Color.fromRGB(
                        new Random().nextInt(0, 256),
                        new Random().nextInt(0, 256),
                        new Random().nextInt(0, 256))
                );

                drop.setItemMeta(meta);
            }

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
