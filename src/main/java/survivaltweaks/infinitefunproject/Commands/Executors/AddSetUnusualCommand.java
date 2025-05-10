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
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;

public class AddSetUnusualCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        ItemStack chosen = player.getInventory().getItemInMainHand();

        if(!UnusualManager.allowedItems.contains(chosen.getType())) {
            player.sendMessage(ChatColor.RED + "Item cannot have an unusual effect.");
            return true;
        }

        if(strings.length != 1) {
            player.sendMessage(ChatColor.RED + "Specify an effect.");
            return true;
        }

        Unusual unusual = Unusual.getByInternalName(strings[0]);

        if(unusual != null) {
            if(hasUnusual(chosen)) {
                replaceUnusual(chosen, unusual);
            } else {
                addUnusual(chosen, unusual);
            }

        } else {
            player.sendMessage(ChatColor.RED + "Couldn't find effect.");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Added effect: " + fixCaps(strings[0]));
        return true;
    }
}
