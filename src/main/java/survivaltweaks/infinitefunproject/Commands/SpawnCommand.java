package survivaltweaks.infinitefunproject.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        Location spawn = player.getBedSpawnLocation();

        player.teleport(spawn);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Teleported you back to your bed spawnpoint");

        return true;
    }
}
