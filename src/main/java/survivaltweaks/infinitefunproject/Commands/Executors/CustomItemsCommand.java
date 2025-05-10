package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import survivaltweaks.infinitefunproject.CustomItems.ItemManager;

public class CustomItemsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        Inventory inventory = Bukkit.createInventory(player, 54, "Custom Items");

        if(strings.length == 1) {
            int page = Integer.parseInt(strings[0]) - 1;
            for(int i = 0; i < inventory.getSize(); i++) {
                if(i + (inventory.getSize() * page) >= ItemManager.customItems.size()) {
                    inventory.setItem(i, ItemManager.comingSoon);
                    continue;
                }

                inventory.setItem(i, ItemManager.customItems.get(i + (inventory.getSize() * page)));
            }
        } else {
            for(int i = 0; i < inventory.getSize(); i++) {
                if(i >= ItemManager.customItems.size()) {
                    inventory.setItem(i, ItemManager.comingSoon);
                    continue;
                }

                inventory.setItem(i, ItemManager.customItems.get(i));
            }
        }

        player.openInventory(inventory);
        return true;
    }
}
