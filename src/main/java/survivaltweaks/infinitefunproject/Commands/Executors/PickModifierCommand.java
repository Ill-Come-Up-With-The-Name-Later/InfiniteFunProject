package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit.pickModifier;

public class PickModifierCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        pickModifier();
        return true;
    }
}
