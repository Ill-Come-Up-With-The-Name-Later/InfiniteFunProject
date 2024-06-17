package survivaltweaks.infinitefunproject.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetDayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length == 1) {
            int day = Integer.parseInt(strings[0]);
            long time = day * 24000L;

            for(World world : Bukkit.getServer().getWorlds()) {
                world.setFullTime(time);
            }
            player.sendMessage(ChatColor.GREEN + "The day has been set to " + day);
            return true;
        }

        player.sendMessage(ChatColor.RED + "You must provide a day.");
        return false;
    }
}
