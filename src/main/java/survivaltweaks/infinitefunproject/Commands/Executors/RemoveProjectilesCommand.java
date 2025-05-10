package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.killProjectiles;

public class RemoveProjectilesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        killProjectiles();
        player.sendMessage(ChatColor.GREEN + "Removed all projectiles.");

        return true;
    }
}
