package survivaltweaks.infinitefunproject.Player.Upgrades;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.Player.Upgrades.InitUpgrades.applyUpgrades;

public class UpgradeGUI implements Listener {

    public static final String menuTitle = ChatColor.GOLD + "Upgrades";
    private static final int size = 18;
    private static final String cannotUpgrade = ChatColor.RED + "Maximum Level Reached.";

    public static void openGUI(Player player) {
        Inventory upgradeUI = Bukkit.createInventory(player, size, menuTitle);

        for(int i = 0; i < size; i++) {
            upgradeUI.setItem(i, ItemManager.placeholder);
        }

        updateIcons(player, upgradeUI);
        player.openInventory(upgradeUI);
    }

    public static void updateIcons(Player player, Inventory upgradeUI) {
        int i = 0;
        for(PlayerUpgrade upgrade : PlayerUpgrade.values()) {
            int level = InitUpgrades.getUpgradeLevel(player, upgrade);

            ItemStack icon = upgrade.getUpgradeIcon().clone();

            ItemMeta iconMeta = icon.getItemMeta();
            iconMeta.setDisplayName(iconMeta.getDisplayName() + " - Current Level: " + ChatColor.GOLD + level);

            if(level >= upgrade.getMaxLevel()) {
                ArrayList<String> lore = (ArrayList<String>) iconMeta.getLore();
                lore.set(lore.size() - 1, cannotUpgrade);

                iconMeta.setLore(lore);
            }

            icon.setItemMeta(iconMeta);
            upgradeUI.setItem(i, icon);
            i++;
        }
    }

    @EventHandler
    public void upgrade(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        String title = event.getView().getTitle();
        Inventory top = event.getView().getTopInventory();

        if(event.getCurrentItem() == null) {
            return;
        }

        int clickedIndex = inventory.first(event.getCurrentItem());

        if(title.equals(menuTitle)) {
            event.setCancelled(true);

            if(inventory.equals(top)) {
                if(clickedIndex < PlayerUpgrade.values().length) {
                    PlayerUpgrade upgrade = PlayerUpgrade.values()[clickedIndex];

                    if(InitUpgrades.getUpgradeLevel(player, upgrade) >= upgrade.getMaxLevel()) {
                        player.sendMessage(ChatColor.RED + "You have already maxed out this upgrade.");
                        return;
                    }

                    if(player.getLevel() < upgrade.getUpgradeCost() && !(player.getGameMode() == GameMode.CREATIVE)) {
                        player.sendMessage(ChatColor.RED + "You cannot afford this upgrade.");
                        return;
                    }

                    if(!(player.getGameMode() == GameMode.CREATIVE)) {
                        player.setLevel(player.getLevel() - upgrade.getUpgradeCost());
                    }

                    InitUpgrades.setUpgradeLevel(player, upgrade, InitUpgrades.nextLevel(player, upgrade));
                    updateIcons(player, top);
                    applyUpgrades(player);
                }
            }
        }
    }
}
