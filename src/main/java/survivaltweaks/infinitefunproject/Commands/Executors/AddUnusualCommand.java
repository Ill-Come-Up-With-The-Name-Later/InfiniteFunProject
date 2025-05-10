package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual;
import survivaltweaks.infinitefunproject.CustomItems.Unusual.UnusualManager;

import static survivaltweaks.infinitefunproject.CustomItems.Unusual.Unusual.*;

public class AddUnusualCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        ItemStack chosen = player.getInventory().getItemInMainHand();

        if(!UnusualManager.allowedItems.contains(chosen.getType())) {
            player.sendMessage(ChatColor.RED + "Item cannot have an unusual effect.");
            return true;
        }

        Unusual unusual = rollRandomEffect();

        if(hasUnusual(chosen)) {
            Unusual.replaceUnusual(chosen, unusual);
        } else {
            Unusual.addUnusual(chosen, unusual);
        }

        player.sendMessage(ChatColor.GREEN + "Added random effect: " + unusual.getName());
        return true;
    }
}
