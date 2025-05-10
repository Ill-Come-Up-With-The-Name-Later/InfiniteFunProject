package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static survivaltweaks.infinitefunproject.TimeEvents.MeteorShower.RainMeteors.*;

public class EndMeteorsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        World world = player.getWorld();

        if(world.getEnvironment() == World.Environment.NETHER) {
            player.sendMessage(ChatColor.RED + "Meteors cannot fall in The Nether.");
            return false;
        }

        if(!hasMeteorShower(world)) {
            player.sendMessage(ChatColor.RED + "Meteors are not falling.");
            return true;
        }

        endShower(world);
        return true;
    }
}
