package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;

public class SetTimerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 2) {
            player.sendMessage(ChatColor.RED + "Timer type and amount of time must be specified.");
            return false;
        }

        String timer = strings[0];
        int newTime = Integer.parseInt(strings[1]);

        switch (timer) {
            case "e":
                EventInit.getCountdown()[0] = newTime;
                break;
            case "m":
                WorldModInit.getCountdown()[0] = newTime;
                break;
        }
        player.sendMessage(ChatColor.GREEN + "Timer has been set to " + newTime + ".");

        return true;
    }
}
