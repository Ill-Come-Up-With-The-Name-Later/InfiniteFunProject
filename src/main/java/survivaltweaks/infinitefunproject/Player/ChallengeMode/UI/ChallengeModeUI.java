package survivaltweaks.infinitefunproject.Player.ChallengeMode.UI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeMode;

import java.util.ArrayList;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;
import static survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager.challengeActive;
import static survivaltweaks.infinitefunproject.Player.ChallengeMode.ChallengeModeManager.save;

public class ChallengeModeUI implements Listener {

    static String title = "Challenges";

    public static void open(Player player) {
        Bukkit.getServer().getScheduler().runTaskLater(InfiniteFunProject.plugin, () -> {
            Inventory inventory = Bukkit.createInventory(player, 18, title);

            for(int i = 0; i < inventory.getSize(); i++) {
                if(i >= ChallengeMode.values().length) {
                    inventory.setItem(i, ItemManager.placeholder);
                    continue;
                }

                ChallengeMode mode = ChallengeMode.values()[i];
                ItemStack icon = new ItemStack(mode.getIcon());

                ItemMeta iconMeta = icon.getItemMeta();

                if(challengeActive(player, mode)) {
                    iconMeta.setDisplayName(ChatColor.GOLD + fixCaps(ChallengeMode.values()[i].getName()));
                } else {
                    iconMeta.setDisplayName(ChatColor.GREEN + fixCaps(ChallengeMode.values()[i].getName()));
                }

                ArrayList<String> lore = new ArrayList<>();
                lore.add("");

                String[] description = mode.getDescription().split("\n");

                for(String s : description) {
                    lore.add(ChatColor.GRAY + s);
                }

                if(challengeActive(player, mode)) {
                    iconMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                    iconMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    lore.add("");
                    lore.add(ChatColor.GOLD + "Active");
                }

                iconMeta.setLore(lore);
                icon.setItemMeta(iconMeta);

                inventory.setItem(i, icon.clone());
            }

            player.openInventory(inventory);
        }, 1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        String invTitle = event.getView().getTitle();
        Inventory top = event.getView().getTopInventory();

        if(inventory.equals(top) && invTitle.equals(title)) {
            event.setCancelled(true);

            ItemStack item = event.getCurrentItem();

            if(item == null) {
                return;
            }

            int index = top.first(item);

            if(index >= ChallengeMode.values().length) {
                return;
            }

            ChallengeMode mode = ChallengeMode.values()[index];

            if(!player.hasMetadata(mode.getDataString())) {
                mode.getManager().apply(player);

                player.closeInventory();
                open(player);
            } else {
                player.sendMessage(ChatColor.GOLD + "Challenge already active.");
            }
        }

        for(ChallengeMode mode : ChallengeMode.values()) {
            save(player, mode);
        }
    }
}
