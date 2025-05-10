package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import survivaltweaks.infinitefunproject.Periodic.Events.EventInit;

public class TriggerEventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        EventInit.pickEvent();

        return true;
    }
}
