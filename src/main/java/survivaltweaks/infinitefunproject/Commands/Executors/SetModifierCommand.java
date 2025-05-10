package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import survivaltweaks.infinitefunproject.InfiniteFunProject;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit.activeModifier;

public class SetModifierCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 1) {
            return false;
        }

        boolean success = false;
        for(WorldModifier worldModifier : WorldModifier.values()) {
            if(strings[0].equalsIgnoreCase(worldModifier.toString())) {
                WorldModInit.setActiveModifier(worldModifier);
                Bukkit.spigot().broadcast(new TextComponent(ChatColor.LIGHT_PURPLE + "The modifier is now " + InfiniteFunProject.fixCaps(worldModifier.toString()) + "!"));
                Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + activeModifier.getDescription()));
                success = true;
                break;
            }
        }
        if(!success) {
            player.sendMessage(ChatColor.RED + "Couldn't find that modifier!");
        }
        return true;
    }
}
