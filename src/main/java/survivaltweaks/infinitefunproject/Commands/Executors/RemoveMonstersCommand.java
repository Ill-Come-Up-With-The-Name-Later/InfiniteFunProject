package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.removeMonsters;

public class RemoveMonstersCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        removeMonsters();
        player.sendMessage(ChatColor.GREEN + "Removed all monsters.");

        return true;
    }
}
