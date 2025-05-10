package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;

public class TriggerAnomalyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        EventInit.pickAnomaly();
        return true;
    }
}
