package survivaltweaks.infinitefunproject.Commands.Executors;

import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit;
import survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModifier;

import static survivaltweaks.infinitefunproject.InfiniteFunProject.color;
import static survivaltweaks.infinitefunproject.InfiniteFunProject.fixCaps;
import static survivaltweaks.infinitefunproject.Periodic.WorldModifiers.WorldModInit.modifierActive;

public class AddBonusModifierCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        if(strings.length != 1) {
            return false;
        }

        boolean success = false;
        for(WorldModifier worldModifier : WorldModifier.values()) {
            if(strings[0].equalsIgnoreCase(worldModifier.toString()) && !modifierActive(worldModifier)) {
                WorldModInit.addBonusModifier(worldModifier);
                Bukkit.spigot().broadcast(new TextComponent(color("&d&lBonus Modifier Added: &6" + fixCaps(worldModifier.toString())
                        + "&d&l!")));
                Bukkit.spigot().broadcast(new TextComponent(ChatColor.GRAY + "- " + ChatColor.YELLOW + worldModifier.getDescription()));

                success = true;
                WorldModInit.getCountdown()[0] = WorldModInit.delay;
                break;
            }
        }
        if(!success) {
            player.sendMessage(ChatColor.RED + "Couldn't find that modifier!");
        }

        return true;
    }
}
