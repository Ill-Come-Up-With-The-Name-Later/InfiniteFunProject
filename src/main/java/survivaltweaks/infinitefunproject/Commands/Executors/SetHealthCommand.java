package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHealthCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 1) {
            player.sendMessage(ChatColor.RED + "One health value must be specified.");
            return false;
        }

        int health = Integer.parseInt(strings[0]);

        player.setHealth(Math.min(health, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()));
        player.sendMessage(ChatColor.GREEN + "Set your health to " + ChatColor.GRAY + health + ChatColor.GREEN + ".");
        return true;
    }
}
